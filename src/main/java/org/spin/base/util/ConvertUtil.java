/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.base.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.model.I_AD_Element;
import org.compiere.model.I_AD_Ref_List;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_ConversionType;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_POSKeyLayout;
import org.compiere.model.MAttachment;
import org.compiere.model.MBPBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MBank;
import org.compiere.model.MBankAccount;
import org.compiere.model.MCharge;
import org.compiere.model.MChatEntry;
import org.compiere.model.MCity;
import org.compiere.model.MClientInfo;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCountry;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLanguage;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.model.MPriceList;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MRefList;
import org.compiere.model.MRegion;
import org.compiere.model.MTax;
import org.compiere.model.MUOM;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.MimeType;
import org.compiere.util.Util;
import org.spin.grpc.util.Address;
import org.spin.grpc.util.Attachment;
import org.spin.grpc.util.BankAccount;
import org.spin.grpc.util.BusinessPartner;
import org.spin.grpc.util.Charge;
import org.spin.grpc.util.ChatEntry;
import org.spin.grpc.util.City;
import org.spin.grpc.util.ConversionRate;
import org.spin.grpc.util.Country;
import org.spin.grpc.util.Currency;
import org.spin.grpc.util.Customer;
import org.spin.grpc.util.CustomerBankAccount;
import org.spin.grpc.util.DocumentAction;
import org.spin.grpc.util.DocumentStatus;
import org.spin.grpc.util.DocumentType;
import org.spin.grpc.util.Entity;
import org.spin.grpc.util.Key;
import org.spin.grpc.util.KeyLayout;
import org.spin.grpc.util.Order;
import org.spin.grpc.util.OrderLine;
import org.spin.grpc.util.Organization;
import org.spin.grpc.util.PriceList;
import org.spin.grpc.util.Product;
import org.spin.grpc.util.Region;
import org.spin.grpc.util.ResourceReference;
import org.spin.grpc.util.SalesRepresentative;
import org.spin.grpc.util.Shipment;
import org.spin.grpc.util.ShipmentLine;
import org.spin.grpc.util.TaxRate;
import org.spin.grpc.util.Value;
import org.spin.grpc.util.Warehouse;
import org.spin.grpc.util.ChatEntry.ModeratorStatus;
import org.spin.model.MADAttachmentReference;
import org.spin.util.AttachmentUtil;
import org.spin.util.VueStoreFrontUtil;

/**
 * Class for convert any document
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ConvertUtil {
	
	/**
	 * Convert PO class from Chat Entry process to builder
	 * @param chatEntry
	 * @return
	 */
	public static ChatEntry.Builder convertChatEntry(MChatEntry chatEntry) {
		ChatEntry.Builder builder = ChatEntry.newBuilder();
		builder.setUuid(ValueUtil.validateNull(chatEntry.getUUID()));
		builder.setId(chatEntry.getCM_ChatEntry_ID());
		builder.setChatUuid(ValueUtil.validateNull(chatEntry.getCM_Chat().getUUID()));
		builder.setSubject(ValueUtil.validateNull(chatEntry.getSubject()));
		builder.setCharacterData(ValueUtil.validateNull(chatEntry.getCharacterData()));
		if(chatEntry.getAD_User_ID() != 0) {
			MUser user = MUser.get(chatEntry.getCtx(), chatEntry.getAD_User_ID());
			builder.setUserUuid(ValueUtil.validateNull(user.getUUID()));
			builder.setUserName(ValueUtil.validateNull(user.getName()));
		}
		builder.setLogDate(chatEntry.getCreated().getTime());
		//	Confidential Type
		if(!Util.isEmpty(chatEntry.getConfidentialType())) {
			if(chatEntry.getConfidentialType().equals(MChatEntry.CONFIDENTIALTYPE_PublicInformation)) {
				builder.setConfidentialType(org.spin.grpc.util.ChatEntry.ConfidentialType.PUBLIC);
			} else if(chatEntry.getConfidentialType().equals(MChatEntry.CONFIDENTIALTYPE_PartnerConfidential)) {
				builder.setConfidentialType(org.spin.grpc.util.ChatEntry.ConfidentialType.PARTER);
			} else if(chatEntry.getConfidentialType().equals(MChatEntry.CONFIDENTIALTYPE_Internal)) {
				builder.setConfidentialType(org.spin.grpc.util.ChatEntry.ConfidentialType.INTERNAL);
			}
		}
		//	Moderator Status
		if(!Util.isEmpty(chatEntry.getModeratorStatus())) {
			if(chatEntry.getModeratorStatus().equals(MChatEntry.MODERATORSTATUS_NotDisplayed)) {
				builder.setModeratorStatus(ModeratorStatus.NOT_DISPLAYED);
			} else if(chatEntry.getModeratorStatus().equals(MChatEntry.MODERATORSTATUS_Published)) {
				builder.setModeratorStatus(ModeratorStatus.PUBLISHED);
			} else if(chatEntry.getModeratorStatus().equals(MChatEntry.MODERATORSTATUS_Suspicious)) {
				builder.setModeratorStatus(ModeratorStatus.SUSPICIUS);
			} else if(chatEntry.getModeratorStatus().equals(MChatEntry.MODERATORSTATUS_ToBeReviewed)) {
				builder.setModeratorStatus(ModeratorStatus.TO_BE_REVIEWED);
			}
		}
		//	Chat entry type
		if(!Util.isEmpty(chatEntry.getChatEntryType())) {
			if(chatEntry.getChatEntryType().equals(MChatEntry.CHATENTRYTYPE_NoteFlat)) {
				builder.setChatEntryType(org.spin.grpc.util.ChatEntry.ChatEntryType.NOTE_FLAT);
			} else if(chatEntry.getChatEntryType().equals(MChatEntry.CHATENTRYTYPE_ForumThreaded)) {
				builder.setChatEntryType(org.spin.grpc.util.ChatEntry.ChatEntryType.NOTE_FLAT);
			} else if(chatEntry.getChatEntryType().equals(MChatEntry.CHATENTRYTYPE_Wiki)) {
				builder.setChatEntryType(org.spin.grpc.util.ChatEntry.ChatEntryType.NOTE_FLAT);
			}
		}
  		return builder;
	}
	
	/**
	 * Convert PO to Value Object
	 * @param entity
	 * @return
	 */
	public static Entity.Builder convertEntity(PO entity) {
		Entity.Builder builder = Entity.newBuilder();
		if(entity == null) {
			return builder;
		}
		builder.setUuid(ValueUtil.validateNull(entity.get_ValueAsString(I_AD_Element.COLUMNNAME_UUID)))
			.setId(entity.get_ID());
		//	Convert attributes
		POInfo poInfo = POInfo.getPOInfo(Env.getCtx(), entity.get_Table_ID());
		builder.setTableName(ValueUtil.validateNull(poInfo.getTableName()));
		for(int index = 0; index < poInfo.getColumnCount(); index++) {
			String columnName = poInfo.getColumnName(index);
			int referenceId = poInfo.getColumnDisplayType(index);
			Object value = entity.get_Value(index);
			if(value == null) {
				continue;
			}
			Value.Builder builderValue = ValueUtil.getValueFromReference(value, referenceId);
			if(builderValue == null) {
				continue;
			}
			//	Add
			builder.putValues(columnName, builderValue.build());
		}
		//	
		return builder;
	}
	
	/**
	 * Convert Document Action
	 * @param value
	 * @param name
	 * @param description
	 * @return
	 */
	public static DocumentAction.Builder convertDocumentAction(String value, String name, String description) {
		return DocumentAction.newBuilder()
				.setValue(ValueUtil.validateNull(value))
				.setName(ValueUtil.validateNull(name))
				.setDescription(ValueUtil.validateNull(description));
	}
	
	/**
	 * Convert Document Status
	 * @param value
	 * @param name
	 * @param description
	 * @return
	 */
	public static DocumentStatus.Builder convertDocumentStatus(String value, String name, String description) {
		return DocumentStatus.newBuilder()
				.setValue(ValueUtil.validateNull(value))
				.setName(ValueUtil.validateNull(name))
				.setDescription(ValueUtil.validateNull(description));
	}
	
	/**
	 * Convert Document Type
	 * @param documentType
	 * @return
	 */
	public static DocumentType.Builder convertDocumentType(MDocType documentType) {
		return DocumentType.newBuilder()
				.setUuid(ValueUtil.validateNull(documentType.getUUID()))
				.setId(documentType.getC_DocType_ID())
				.setName(ValueUtil.validateNull(documentType.getName()))
				.setDescription(ValueUtil.validateNull(documentType.getDescription()))
				.setPrintName(ValueUtil.validateNull(documentType.getPrintName()));
	}
	
	/**
	 * Convert business partner
	 * @param businessPartner
	 * @return
	 */
	public static BusinessPartner.Builder convertBusinessPartner(MBPartner businessPartner) {
		if(businessPartner == null) {
			return BusinessPartner.newBuilder();
		}
		return BusinessPartner.newBuilder()
				.setUuid(ValueUtil.validateNull(businessPartner.getUUID()))
				.setId(businessPartner.getC_BPartner_ID())
				.setValue(ValueUtil.validateNull(businessPartner.getValue()))
				.setTaxId(ValueUtil.validateNull(businessPartner.getTaxID()))
				.setDuns(ValueUtil.validateNull(businessPartner.getDUNS()))
				.setNaics(ValueUtil.validateNull(businessPartner.getNAICS()))
				.setName(ValueUtil.validateNull(businessPartner.getName()))
				.setLastName(ValueUtil.validateNull(businessPartner.getName2()))
				.setDescription(ValueUtil.validateNull(businessPartner.getDescription()));
	}
	
	/**
	 * Convert charge from 
	 * @param chargeId
	 * @return
	 */
	public static Charge.Builder convertCharge(MCharge charge) {
		Charge.Builder builder = Charge.newBuilder();
		if(charge == null) {
			return builder;
		}
		//	convert charge
		return builder
			.setUuid(ValueUtil.validateNull(charge.getUUID()))
			.setId(charge.getC_Charge_ID())
			.setName(ValueUtil.validateNull(charge.getName()))
			.setDescription(ValueUtil.validateNull(charge.getDescription()));
	}
	
	/**
	 * Convert charge from 
	 * @param chargeId
	 * @return
	 */
	public static ConversionRate.Builder convertConversionRate(MConversionRate conversionRate) {
		ConversionRate.Builder builder = ConversionRate.newBuilder();
		if(conversionRate == null) {
			return builder;
		}
		//	convert charge
		builder
			.setUuid(ValueUtil.validateNull(conversionRate.getUUID()))
			.setId(conversionRate.getC_Conversion_Rate_ID())
			.setValidFrom(ValueUtil.validateNull(ValueUtil.convertDateToString(conversionRate.getValidFrom())))
			.setConversionTypeUuid(ValueUtil.validateNull(RecordUtil.getUuidFromId(I_C_ConversionType.Table_Name, conversionRate.getC_ConversionType_ID())))
			.setCurrencyFrom(convertCurrency(MCurrency.get(Env.getCtx(), conversionRate.getC_Currency_ID())))
			.setCurrencyTo(convertCurrency(MCurrency.get(Env.getCtx(), conversionRate.getC_Currency_ID_To())))
			.setMultiplyRate(ValueUtil.getDecimalFromBigDecimal(conversionRate.getMultiplyRate()))
			.setDivideRate(ValueUtil.getDecimalFromBigDecimal(conversionRate.getDivideRate()));
		if(conversionRate.getValidTo() != null) {
			builder.setValidTo(ValueUtil.validateNull(ValueUtil.convertDateToString(conversionRate.getValidTo())));
		}
		//	
		return builder;
	}
	
	/**
	 * Convert Product to 
	 * @param product
	 * @return
	 */
	public static Product.Builder convertProduct(MProduct product) {
		Product.Builder builder = Product.newBuilder();
		builder.setUuid(ValueUtil.validateNull(product.getUUID()))
				.setId(product.getM_Product_ID())
				.setValue(ValueUtil.validateNull(product.getValue()))
				.setName(ValueUtil.validateNull(product.getName()))
				.setDescription(ValueUtil.validateNull(product.getDescription()))
				.setHelp(ValueUtil.validateNull(product.getHelp()))
				.setDocumentNote(ValueUtil.validateNull(product.getDocumentNote()))
				.setUomName(ValueUtil.validateNull(MUOM.get(product.getCtx(), product.getC_UOM_ID()).getName()))
				.setDescriptionUrl(ValueUtil.validateNull(product.getDescriptionURL()))
				//	Product Type
				.setIsStocked(product.isStocked())
				.setIsDropShip(product.isDropShip())
				.setIsPurchased(product.isPurchased())
				.setIsSold(product.isSold())
				.setImageUrl(ValueUtil.validateNull(product.getImageURL()))
				.setUpc(ValueUtil.validateNull(product.getUPC()))
				.setSku(ValueUtil.validateNull(product.getSKU()))
				.setVersionNo(ValueUtil.validateNull(product.getVersionNo()))
				.setGuaranteeDays(product.getGuaranteeDays())
				.setWeight(ValueUtil.getDecimalFromBigDecimal(product.getWeight()))
				.setVolume(ValueUtil.getDecimalFromBigDecimal(product.getVolume()))
				.setShelfDepth(product.getShelfDepth())
				.setShelfHeight(ValueUtil.getDecimalFromBigDecimal(product.getShelfHeight()))
				.setShelfWidth(product.getShelfWidth())
				.setUnitsPerPallet(ValueUtil.getDecimalFromBigDecimal(product.getUnitsPerPallet()))
				.setUnitsPerPack(product.getUnitsPerPack())
				.setTaxCategory(ValueUtil.validateNull(product.getC_TaxCategory().getName()))
				.setProductCategoryName(ValueUtil.validateNull(MProductCategory.get(product.getCtx(), product.getM_Product_Category_ID()).getName()));
		//	Group
		if(product.getM_Product_Group_ID() != 0) {
			builder.setProductGroupName(ValueUtil.validateNull(product.getM_Product_Group().getName()));
		}
		//	Class
		if(product.getM_Product_Class_ID() != 0) {
			builder.setProductClassName(ValueUtil.validateNull(product.getM_Product_Class().getName()));
		}
		//	Classification
		if(product.getM_Product_Classification_ID() != 0) {
			builder.setProductClassificationName(ValueUtil.validateNull(product.getM_Product_Classification().getName()));
		}
		return builder;
	}
	
	/**
	 * Convert Language to gRPC
	 * @param language
	 * @return
	 */
	public static org.spin.grpc.util.Language.Builder convertLanguage(MLanguage language) {
		String datePattern = language.getDatePattern();
		String timePattern = language.getTimePattern();
		if(Util.isEmpty(datePattern)) {
			org.compiere.util.Language staticLanguage = org.compiere.util.Language.getLanguage(language.getAD_Language());
			if(staticLanguage != null) {
				datePattern = staticLanguage.getDateFormat().toPattern();
			}
			//	Validate
			if(Util.isEmpty(datePattern)) {
				datePattern = language.getDateFormat().toPattern();
			}
		}
		if(Util.isEmpty(timePattern)) {
			org.compiere.util.Language staticLanguage = org.compiere.util.Language.getLanguage(language.getAD_Language());
			if(staticLanguage != null) {
				timePattern = staticLanguage.getTimeFormat().toPattern();
			}
		}
		return org.spin.grpc.util.Language.newBuilder()
				.setLanguage(ValueUtil.validateNull(language.getAD_Language()))
				.setCountryCode(ValueUtil.validateNull(language.getCountryCode()))
				.setLanguageIso(ValueUtil.validateNull(language.getLanguageISO()))
				.setLanguageName(ValueUtil.validateNull(language.getName()))
				.setDatePattern(ValueUtil.validateNull(datePattern))
				.setTimePattern(ValueUtil.validateNull(timePattern))
				.setIsBaseLanguage(language.isBaseLanguage())
				.setIsSystemLanguage(language.isSystemLanguage())
				.setIsDecimalPoint(language.isDecimalPoint());
	}
	
	/**
	 * Convert Country
	 * @param context
	 * @param country
	 * @return
	 */
	public static Country.Builder convertCountry(Properties context, MCountry country) {
		Country.Builder builder = Country.newBuilder();
		if(country == null) {
			return builder;
		}
		builder.setUuid(ValueUtil.validateNull(country.getUUID()))
			.setId(country.getC_Country_ID())
			.setCountryCode(ValueUtil.validateNull(country.getCountryCode()))
			.setName(ValueUtil.validateNull(country.getName()))
			.setDescription(ValueUtil.validateNull(country.getDescription()))
			.setHasRegion(country.isHasRegion())
			.setRegionName(ValueUtil.validateNull(country.getRegionName()))
			.setDisplaySequence(ValueUtil.validateNull(country.getDisplaySequence()))
			.setIsAddressLinesReverse(country.isAddressLinesReverse())
			.setCaptureSequence(ValueUtil.validateNull(country.getCaptureSequence()))
			.setDisplaySequenceLocal(ValueUtil.validateNull(country.getDisplaySequenceLocal()))
			.setIsAddressLinesLocalReverse(country.isAddressLinesLocalReverse())
			.setHasPostalAdd(country.isHasPostal_Add())
			.setExpressionPhone(ValueUtil.validateNull(country.getExpressionPhone()))
			.setMediaSize(ValueUtil.validateNull(country.getMediaSize()))
			.setExpressionBankRoutingNo(ValueUtil.validateNull(country.getExpressionBankRoutingNo()))
			.setExpressionBankAccountNo(ValueUtil.validateNull(country.getExpressionBankAccountNo()))
			.setAllowCitiesOutOfList(country.isAllowCitiesOutOfList())
			.setIsPostcodeLookup(country.isPostcodeLookup())
			.setLanguage(ValueUtil.validateNull(country.getAD_Language()));
		//	Set Currency
		if(country.getC_Currency_ID() != 0) {
			builder.setCurrency(convertCurrency(MCurrency.get(context, country.getC_Currency_ID())));
		}
		//	
		return builder;
	}
	
	/**
	 * Convert Currency
	 * @param currency
	 * @return
	 */
	public static Currency.Builder convertCurrency(MCurrency currency) {
		Currency.Builder builder = Currency.newBuilder();
		if(currency == null) {
			return builder;
		}
		//	Set values
		return builder.setUuid(ValueUtil.validateNull(currency.getUUID()))
			.setId(currency.getC_Currency_ID())
			.setIsoCode(ValueUtil.validateNull(currency.getISO_Code()))
			.setCurSymbol(ValueUtil.validateNull(currency.getCurSymbol()))
			.setDescription(ValueUtil.validateNull(currency.getDescription()))
			.setStandardPrecision(currency.getStdPrecision())
			.setCostingPrecision(currency.getCostingPrecision());
	}
	
	/**
	 * Convert Price List
	 * @param priceList
	 * @return
	 */
	public static PriceList.Builder convertPriceList(MPriceList priceList) {
		PriceList.Builder builder = PriceList.newBuilder();
		if(priceList == null) {
			return builder;
		}
		//	
		return builder.setUuid(ValueUtil.validateNull(priceList.getUUID()))
			.setId(priceList.getM_PriceList_ID())
			.setName(ValueUtil.validateNull(priceList.getName()))
			.setDescription(ValueUtil.validateNull(priceList.getDescription()))
			.setCurrency(convertCurrency(MCurrency.get(priceList.getCtx(), priceList.getC_Currency_ID())))
			.setIsDefault(priceList.isDefault())
			.setIsTaxIncluded(priceList.isTaxIncluded())
			.setIsEnforcePriceLimit(priceList.isEnforcePriceLimit())
			.setIsNetPrice(priceList.isNetPrice())
			.setPricePrecision(priceList.getPricePrecision());
	}
	
	/**
	 * Convert Order from entity
	 * @param order
	 * @return
	 */
	public static  Order.Builder convertOrder(MOrder order) {
		Order.Builder builder = Order.newBuilder();
		if(order == null) {
			return builder;
		}
		MRefList reference = MRefList.get(Env.getCtx(), MOrder.DOCSTATUS_AD_REFERENCE_ID, order.getDocStatus(), null);
		//	Convert
		return builder
			.setUuid(ValueUtil.validateNull(order.getUUID()))
			.setId(order.getC_Order_ID())
			.setDocumentType(ConvertUtil.convertDocumentType(MDocType.get(Env.getCtx(), order.getC_DocTypeTarget_ID())))
			.setDocumentNo(ValueUtil.validateNull(order.getDocumentNo()))
			.setSalesRepresentative(convertSalesRepresentative(MUser.get(Env.getCtx(), order.getSalesRep_ID())))
			.setDocumentStatus(ConvertUtil.convertDocumentStatus(
					ValueUtil.validateNull(order.getDocStatus()), 
					ValueUtil.validateNull(ValueUtil.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Name)), 
					ValueUtil.validateNull(ValueUtil.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Description))))
			.setPriceList(ConvertUtil.convertPriceList(MPriceList.get(Env.getCtx(), order.getM_PriceList_ID(), order.get_TrxName())))
			.setWarehouse(convertWarehouse(order.getM_Warehouse_ID()))
			.setTotalLines(ValueUtil.getDecimalFromBigDecimal(order.getTotalLines()))
			.setGrandTotal(ValueUtil.getDecimalFromBigDecimal(order.getGrandTotal()))
			.setDateOrdered(ValueUtil.convertDateToString(order.getDateOrdered()))
			.setCustomer(convertCustomer((MBPartner) order.getC_BPartner()));
	}
	
	/**
	 * Convert customer bank account
	 * @param customerBankAccount
	 * @return
	 * @return CustomerBankAccount.Builder
	 */
	public static CustomerBankAccount.Builder convertCustomerBankAccount(MBPBankAccount customerBankAccount) {
		CustomerBankAccount.Builder builder = CustomerBankAccount.newBuilder();
		builder.setCustomerBankAccountUuid(ValueUtil.validateNull(customerBankAccount.getUUID()))
			.setCity(ValueUtil.validateNull(customerBankAccount.getA_City()))
			.setCountry(ValueUtil.validateNull(customerBankAccount.getA_Country()))
			.setEmail(ValueUtil.validateNull(customerBankAccount.getA_EMail()))
			.setDriverLicense(ValueUtil.validateNull(customerBankAccount.getA_Ident_DL()))
			.setSocialSecurityNumber(ValueUtil.validateNull(customerBankAccount.getA_Ident_SSN()))
			.setName(ValueUtil.validateNull(customerBankAccount.getA_Name()))
			.setState(ValueUtil.validateNull(customerBankAccount.getA_State()))
			.setStreet(ValueUtil.validateNull(customerBankAccount.getA_Street()))
			.setZip(ValueUtil.validateNull(customerBankAccount.getA_Zip()))
			.setBankAccountType(ValueUtil.validateNull(customerBankAccount.getBankAccountType()));
		if(customerBankAccount.getC_Bank_ID() > 0) {
			MBank bank = MBank.get(Env.getCtx(), customerBankAccount.getC_Bank_ID());
			builder.setBankUuid(ValueUtil.validateNull(bank.getUUID()));
		}
		MBPartner customer = MBPartner.get(Env.getCtx(), customerBankAccount.getC_BPartner_ID());
		builder.setCustomerUuid(ValueUtil.validateNull(customer.getUUID()));
		builder.setAddressVerified(ValueUtil.validateNull(customerBankAccount.getR_AvsAddr()))
			.setZipVerified(ValueUtil.validateNull(customerBankAccount.getR_AvsZip()))
			.setRoutingNo(ValueUtil.validateNull(customerBankAccount.getRoutingNo()))
			.setIban(ValueUtil.validateNull(customerBankAccount.getIBAN())) ;
		return builder;
	}
	
	/**
	 * Convert Order from entity
	 * @param shipment
	 * @return
	 */
	public static  Shipment.Builder convertShipment(MInOut shipment) {
		Shipment.Builder builder = Shipment.newBuilder();
		if(shipment == null) {
			return builder;
		}
		MRefList reference = MRefList.get(Env.getCtx(), MOrder.DOCSTATUS_AD_REFERENCE_ID, shipment.getDocStatus(), null);
		MOrder order = (MOrder) shipment.getC_Order();
		//	Convert
		return builder
			.setUuid(ValueUtil.validateNull(shipment.getUUID()))
			.setOrderUuid(ValueUtil.validateNull(order.getUUID()))
			.setId(shipment.getM_InOut_ID())
			.setDocumentType(ConvertUtil.convertDocumentType(MDocType.get(Env.getCtx(), shipment.getC_DocType_ID())))
			.setDocumentNo(ValueUtil.validateNull(shipment.getDocumentNo()))
			.setSalesRepresentative(convertSalesRepresentative(MUser.get(Env.getCtx(), shipment.getSalesRep_ID())))
			.setDocumentStatus(ConvertUtil.convertDocumentStatus(
					ValueUtil.validateNull(shipment.getDocStatus()), 
					ValueUtil.validateNull(ValueUtil.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Name)), 
					ValueUtil.validateNull(ValueUtil.getTranslation(reference, I_AD_Ref_List.COLUMNNAME_Description))))
			.setWarehouse(convertWarehouse(shipment.getM_Warehouse_ID()))
			.setMovementDate(ValueUtil.convertDateToString(shipment.getMovementDate()));
	}
	
	/**
	 * Convert order line to stub
	 * @param orderLine
	 * @return
	 */
	public static OrderLine.Builder convertOrderLine(MOrderLine orderLine) {
		OrderLine.Builder builder = OrderLine.newBuilder();
		if(orderLine == null) {
			return builder;
		}
		//	Convert
		return builder
				.setUuid(ValueUtil.validateNull(orderLine.getUUID()))
				.setOrderUuid(ValueUtil.validateNull(RecordUtil.getUuidFromId(I_C_Order.Table_Name, orderLine.getC_Order_ID())))
				.setLine(orderLine.getLine())
				.setDescription(ValueUtil.validateNull(orderLine.getDescription()))
				.setLineDescription(ValueUtil.validateNull(orderLine.getName()))
				.setProduct(convertProduct(orderLine.getM_Product_ID()))
				.setCharge(convertCharge(orderLine.getC_Charge_ID()))
				.setWarehouse(convertWarehouse(orderLine.getM_Warehouse_ID()))
				.setQuantity(ValueUtil.getDecimalFromBigDecimal(orderLine.getQtyOrdered()))
				.setPrice(ValueUtil.getDecimalFromBigDecimal(orderLine.getPriceActual()))
				.setPriceList(ValueUtil.getDecimalFromBigDecimal(orderLine.getPriceList()))
				.setDiscountRate(ValueUtil.getDecimalFromBigDecimal(orderLine.getDiscount()))
				.setTaxRate(ConvertUtil.convertTaxRate(MTax.get(Env.getCtx(), orderLine.getC_Tax_ID())))
				.setLineNetAmount(ValueUtil.getDecimalFromBigDecimal(orderLine.getLineNetAmt()));
	}
	
	/**
	 * Convert shipment line to stub
	 * @param shipmentLine
	 * @return
	 */
	public static ShipmentLine.Builder convertShipmentLine(MInOutLine shipmentLine) {
		ShipmentLine.Builder builder = ShipmentLine.newBuilder();
		if(shipmentLine == null) {
			return builder;
		}
		MOrderLine orderLine = (MOrderLine) shipmentLine.getC_OrderLine();
		//	Convert
		return builder
				.setUuid(ValueUtil.validateNull(shipmentLine.getUUID()))
				.setOrderLineUuid(ValueUtil.validateNull(orderLine.getUUID()))
				.setId(shipmentLine.getM_InOutLine_ID())
				.setLine(shipmentLine.getLine())
				.setDescription(ValueUtil.validateNull(shipmentLine.getDescription()))
				.setProduct(convertProduct(shipmentLine.getM_Product_ID()))
				.setCharge(convertCharge(shipmentLine.getC_Charge_ID()))
				.setQuantity(ValueUtil.getDecimalFromBigDecimal(shipmentLine.getMovementQty()));
	}
	
	/**
	 * Convert product
	 * @param productId
	 * @return
	 */
	public static Product.Builder convertProduct(int productId) {
		Product.Builder builder = Product.newBuilder();
		if(productId <= 0) {
			return builder;
		}
		return ConvertUtil.convertProduct(MProduct.get(Env.getCtx(), productId));
	}
	
	/**
	 * Convert charge
	 * @param chargeId
	 * @return
	 */
	public static Charge.Builder convertCharge(int chargeId) {
		Charge.Builder builder = Charge.newBuilder();
		if(chargeId <= 0) {
			return builder;
		}
		return ConvertUtil.convertCharge(MCharge.get(Env.getCtx(), chargeId));
	}
	
	/**
	 * convert warehouse from id
	 * @param warehouseId
	 * @return
	 */
	public static Warehouse.Builder convertWarehouse(int warehouseId) {
		Warehouse.Builder builder = Warehouse.newBuilder();
		if(warehouseId <= 0) {
			return builder;
		}
		return ConvertUtil.convertWarehouse(MWarehouse.get(Env.getCtx(), warehouseId));
	}
	
	/**
	 * Convert key layout from id
	 * @param keyLayoutId
	 * @return
	 */
	public static KeyLayout.Builder convertKeyLayout(int keyLayoutId) {
		KeyLayout.Builder builder = KeyLayout.newBuilder();
		if(keyLayoutId <= 0) {
			return builder;
		}
		return convertKeyLayout(MPOSKeyLayout.get(Env.getCtx(), keyLayoutId));
	}
	
	/**
	 * Convert Key Layout from PO
	 * @param keyLayout
	 * @return
	 */
	public static KeyLayout.Builder convertKeyLayout(MPOSKeyLayout keyLayout) {
		KeyLayout.Builder builder = KeyLayout.newBuilder()
				.setUuid(ValueUtil.validateNull(keyLayout.getUUID()))
				.setId(keyLayout.getC_POSKeyLayout_ID())
				.setName(ValueUtil.validateNull(keyLayout.getName()))
				.setDescription(ValueUtil.validateNull(keyLayout.getDescription()))
				.setHelp(ValueUtil.validateNull(keyLayout.getHelp()))
				.setLayoutType(ValueUtil.validateNull(keyLayout.getPOSKeyLayoutType()))
				.setColumns(keyLayout.getColumns());
				//	TODO: Color
		//	Add keys
		Arrays.asList(keyLayout.getKeys(false)).stream().filter(key -> key.isActive()).forEach(key -> builder.addKeys(convertKey(key)));
		return builder;
	}
	
	/**
	 * Convet key for layout
	 * @param key
	 * @return
	 */
	public static Key.Builder convertKey(MPOSKey key) {
		String productValue = null;
		if(key.getM_Product_ID() > 0) {
			productValue = MProduct.get(Env.getCtx(), key.getM_Product_ID()).getValue();
		}
		return Key.newBuilder()
				.setUuid(ValueUtil.validateNull(key.getUUID()))
				.setId(key.getC_POSKeyLayout_ID())
				.setName(ValueUtil.validateNull(key.getName()))
				//	TODO: Color
				.setSequence(key.getSeqNo())
				.setSpanX(key.getSpanX())
				.setSpanY(key.getSpanY())
				.setSubKeyLayoutUuid(ValueUtil.validateNull(RecordUtil.getUuidFromId(I_C_POSKeyLayout.Table_Name, key.getSubKeyLayout_ID())))
				.setQuantity(ValueUtil.getDecimalFromBigDecimal(Optional.ofNullable(key.getQty()).orElse(Env.ZERO)))
				.setProductValue(ValueUtil.validateNull(productValue))
				.setResourceReference(ConvertUtil.convertResourceReference(RecordUtil.getResourceFromImageId(key.getAD_Image_ID())));
	}
	
	/**
	 * Convert Sales Representative
	 * @param salesRepresentative
	 * @return
	 */
	public static SalesRepresentative.Builder convertSalesRepresentative(MUser salesRepresentative) {
		return SalesRepresentative.newBuilder()
				.setUuid(ValueUtil.validateNull(salesRepresentative.getUUID()))
				.setId(salesRepresentative.getAD_User_ID())
				.setName(ValueUtil.validateNull(salesRepresentative.getName()))
				.setDescription(ValueUtil.validateNull(salesRepresentative.getDescription()));
	}
	
	/**
	 * Convert customer
	 * @param businessPartner
	 * @return
	 */
	public static Customer.Builder convertCustomer(MBPartner businessPartner) {
		if(businessPartner == null) {
			return Customer.newBuilder();
		}
		Customer.Builder customer = Customer.newBuilder()
				.setUuid(ValueUtil.validateNull(businessPartner.getUUID()))
				.setId(businessPartner.getC_BPartner_ID())
				.setValue(ValueUtil.validateNull(businessPartner.getValue()))
				.setTaxId(ValueUtil.validateNull(businessPartner.getTaxID()))
				.setDuns(ValueUtil.validateNull(businessPartner.getDUNS()))
				.setNaics(ValueUtil.validateNull(businessPartner.getNAICS()))
				.setName(ValueUtil.validateNull(businessPartner.getName()))
				.setLastName(ValueUtil.validateNull(businessPartner.getName2()))
				.setDescription(ValueUtil.validateNull(businessPartner.getDescription()));
		//	Add Address
		Arrays.asList(businessPartner.getLocations(true)).stream().filter(customerLocation -> customerLocation.isActive()).forEach(address -> customer.addAddresses(convertCustomerAddress(address)));
		return customer;
	}
	
	/**
	 * Convert Address
	 * @param businessPartnerLocation
	 * @return
	 * @return Address.Builder
	 */
	public static Address.Builder convertCustomerAddress(MBPartnerLocation businessPartnerLocation) {
		if(businessPartnerLocation == null) {
			return Address.newBuilder();
		}
		MLocation location = businessPartnerLocation.getLocation(true);
		Address.Builder builder =  Address.newBuilder()
				.setUuid(ValueUtil.validateNull(businessPartnerLocation.getUUID()))
				.setId(businessPartnerLocation.getC_BPartner_Location_ID())
				.setPostalCode(ValueUtil.validateNull(location.getPostal()))
				.setAddress1(ValueUtil.validateNull(location.getAddress1()))
				.setAddress2(ValueUtil.validateNull(location.getAddress2()))
				.setAddress3(ValueUtil.validateNull(location.getAddress3()))
				.setAddress4(ValueUtil.validateNull(location.getAddress4()))
				.setPostalCode(ValueUtil.validateNull(location.getPostal()))
				.setDescription(ValueUtil.validateNull(businessPartnerLocation.get_ValueAsString("Description")))
				.setFirstName(ValueUtil.validateNull(businessPartnerLocation.getName()))
				.setLastName(ValueUtil.validateNull(businessPartnerLocation.get_ValueAsString("Name2")))
				.setContactName(ValueUtil.validateNull(businessPartnerLocation.get_ValueAsString("ContactName")))
				.setEmail(ValueUtil.validateNull(businessPartnerLocation.getEMail()))
				.setPhone(ValueUtil.validateNull(businessPartnerLocation.getPhone()))
				.setIsDefaultShipping(businessPartnerLocation.get_ValueAsBoolean(VueStoreFrontUtil.COLUMNNAME_IsDefaultShipping))
				.setIsDefaultBilling(businessPartnerLocation.get_ValueAsBoolean(VueStoreFrontUtil.COLUMNNAME_IsDefaultBilling));
		//	Get user from location
		MUser user = new Query(Env.getCtx(), I_AD_User.Table_Name, I_AD_User.COLUMNNAME_C_BPartner_Location_ID + " = ?", businessPartnerLocation.get_TrxName())
				.setParameters(businessPartnerLocation.getC_BPartner_Location_ID())
				.setOnlyActiveRecords(true)
				.first();
		String phone = null;
		if(user != null
				&& user.getAD_User_ID() > 0) {
			if(Util.isEmpty(user.getPhone())) {
				phone = user.getPhone();
			}
			if(!Util.isEmpty(user.getName())
					&& Util.isEmpty(builder.getContactName())) {
				builder.setContactName(user.getName());
			}
		}
		//	
		builder.setPhone(ValueUtil.validateNull(Optional.ofNullable(businessPartnerLocation.getPhone()).orElse(Optional.ofNullable(phone).orElse(""))));
		MCountry country = MCountry.get(Env.getCtx(), location.getC_Country_ID());
		builder.setCountryCode(ValueUtil.validateNull(country.getCountryCode()))
			.setCountryUuid(ValueUtil.validateNull(country.getUUID()))
			.setCountryId(country.getC_Country_ID());
		//	City
		if(location.getC_City_ID() > 0) {
			MCity city = MCity.get(Env.getCtx(), location.getC_City_ID());
			builder.setCity(City.newBuilder()
					.setId(city.getC_City_ID())
					.setUuid(ValueUtil.validateNull(city.getUUID()))
					.setName(ValueUtil.validateNull(city.getName())));
		} else {
			builder.setCity(City.newBuilder()
					.setName(ValueUtil.validateNull(location.getCity())));
		}
		//	Region
		if(location.getC_Region_ID() > 0) {
			MRegion region = MRegion.get(Env.getCtx(), location.getC_Region_ID());
			builder.setRegion(Region.newBuilder()
					.setId(region.getC_Region_ID())
					.setUuid(ValueUtil.validateNull(region.getUUID()))
					.setName(ValueUtil.validateNull(region.getName())));
		}
		//	
		return builder;
	}
	
	/**
	 * Convert Bank Account to gRPC stub class
	 * @param bankAccount
	 * @return
	 */
	public static BankAccount.Builder convertBankAccount(MBankAccount bankAccount) {
		BankAccount.Builder builder = BankAccount.newBuilder();
		if(bankAccount == null) {
			return builder;
		}
		//	
		return builder.setUuid(ValueUtil.validateNull(bankAccount.getUUID()))
			.setId(bankAccount.getAD_Org_ID())
			.setAccountNo(ValueUtil.validateNull(bankAccount.getAccountNo()))
			.setName(ValueUtil.validateNull(bankAccount.getName()))
			.setDescription(ValueUtil.validateNull(bankAccount.getDescription()))
			.setIsDefault(bankAccount.isDefault())
			.setBban(ValueUtil.validateNull(bankAccount.getBBAN()))
			.setIban(ValueUtil.validateNull(bankAccount.getIBAN()))
			.setBankAccountType(bankAccount.getBankAccountType().equals(MBankAccount.BANKACCOUNTTYPE_Checking)? BankAccount.BankAccountType.CHECKING: BankAccount.BankAccountType.SAVINGS)
			.setCreditLimit(ValueUtil.getDecimalFromBigDecimal(bankAccount.getCreditLimit()))
			.setCurrentBalance(ValueUtil.getDecimalFromBigDecimal(bankAccount.getCurrentBalance()))
			//	Foreign
			.setCurrency(convertCurrency(MCurrency.get(bankAccount.getCtx(), bankAccount.getC_Currency_ID())))
			.setBusinessPartner(ConvertUtil.convertBusinessPartner(MBPartner.get(Env.getCtx(), bankAccount.getC_BPartner_ID())));
	}
	
	/**
	 * Convert organization
	 * @param organization
	 * @return
	 */
	public static Organization.Builder convertOrganization(MOrg organization) {
		MOrgInfo organizationInfo = MOrgInfo.get(Env.getCtx(), organization.getAD_Org_ID(), null);
		AtomicReference<String> corporateImageBranding = new AtomicReference<String>();
		if(organizationInfo.getCorporateBrandingImage_ID() > 0 && AttachmentUtil.getInstance().isValidForClient(organizationInfo.getAD_Client_ID())) {
			MClientInfo clientInfo = MClientInfo.get(Env.getCtx(), organizationInfo.getAD_Client_ID());
			MADAttachmentReference attachmentReference = MADAttachmentReference.getByImageId(Env.getCtx(), clientInfo.getFileHandler_ID(), organizationInfo.getCorporateBrandingImage_ID(), null);
			if(attachmentReference != null
					&& attachmentReference.getAD_AttachmentReference_ID() > 0) {
				corporateImageBranding.set(attachmentReference.getValidFileName());
			}
		}
		return Organization.newBuilder()
				.setCorporateBrandingImage(ValueUtil.validateNull(corporateImageBranding.get()))
				.setUuid(ValueUtil.validateNull(organization.getUUID()))
				.setId(organization.getAD_Org_ID())
				.setName(ValueUtil.validateNull(organization.getName()))
				.setDescription(ValueUtil.validateNull(organization.getDescription()))
				.setDuns(ValueUtil.validateNull(organizationInfo.getDUNS()))
				.setTaxId(ValueUtil.validateNull(organizationInfo.getTaxID()))
				.setPhone(ValueUtil.validateNull(organizationInfo.getPhone()))
				.setPhone2(ValueUtil.validateNull(organizationInfo.getPhone2()))
				.setFax(ValueUtil.validateNull(organizationInfo.getFax()))
				.setIsReadOnly(false);
	}
	
	/**
	 * Convert warehouse
	 * @param warehouse
	 * @return
	 */
	public static Warehouse.Builder convertWarehouse(MWarehouse warehouse) {
		return Warehouse.newBuilder()
				.setUuid(ValueUtil.validateNull(warehouse.getUUID()))
				.setId(warehouse.getM_Warehouse_ID())
				.setName(ValueUtil.validateNull(warehouse.getName()))
				.setDescription(ValueUtil.validateNull(warehouse.getDescription()));
	}
	
	/**
	 * Convert resource
	 * @param reference
	 * @return
	 */
	public static ResourceReference.Builder convertResourceReference(MADAttachmentReference reference) {
		if(reference == null) {
			return ResourceReference.newBuilder();
		}
		return ResourceReference.newBuilder()
				.setResourceUuid(ValueUtil.validateNull(reference.getUUID()))
				.setFileName(ValueUtil.validateNull(reference.getValidFileName()))
				.setDescription(ValueUtil.validateNull(reference.getDescription()))
				.setTextMsg(ValueUtil.validateNull(reference.getTextMsg()))
				.setContentType(ValueUtil.validateNull(MimeType.getMimeType(reference.getFileName())))
				.setFileSize(ValueUtil.getDecimalFromBigDecimal(reference.getFileSize()));
		
	}
	
	/**
	 * Convert Attachment to gRPC
	 * @param attachment
	 * @return
	 */
	public static Attachment.Builder convertAttachment(MAttachment attachment) {
		if(attachment == null) {
			return Attachment.newBuilder();
		}
		Attachment.Builder builder = Attachment.newBuilder()
				.setAttachmentUuid(ValueUtil.validateNull(attachment.getUUID()))
				.setTitle(ValueUtil.validateNull(attachment.getTitle()))
				.setTextMsg(ValueUtil.validateNull(attachment.getTextMsg()));
		MClientInfo clientInfo = MClientInfo.get(attachment.getCtx());
		if(clientInfo.getFileHandler_ID() != 0) {
			MADAttachmentReference.getListByAttachmentId(attachment.getCtx(), clientInfo.getFileHandler_ID(), attachment.getAD_Attachment_ID(), attachment.get_TrxName())
				.forEach(attachmentReference -> builder.addResourceReferences(convertResourceReference(attachmentReference)));
		}
		return builder;
	}
	
	/**
	 * Convert tax to gRPC
	 * @param tax
	 * @return
	 */
	public static TaxRate.Builder convertTaxRate(MTax tax) {
		return TaxRate.newBuilder().setName(ValueUtil.validateNull(tax.getName()))
			.setDescription(ValueUtil.validateNull(tax.getDescription()))
			.setTaxIndicator(ValueUtil.validateNull(tax.getTaxIndicator()))
			.setRate(ValueUtil.getDecimalFromBigDecimal(tax.getRate()));
	}
}
