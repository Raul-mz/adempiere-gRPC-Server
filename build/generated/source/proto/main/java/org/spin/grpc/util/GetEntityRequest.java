// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: businessdata.proto

package org.spin.grpc.util;

/**
 * <pre>
 * Get Entity Request
 * </pre>
 *
 * Protobuf type {@code data.GetEntityRequest}
 */
public  final class GetEntityRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:data.GetEntityRequest)
    GetEntityRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetEntityRequest.newBuilder() to construct.
  private GetEntityRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetEntityRequest() {
    uuid_ = "";
    tableId_ = 0;
    recordId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetEntityRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            uuid_ = s;
            break;
          }
          case 16: {

            tableId_ = input.readInt32();
            break;
          }
          case 24: {

            recordId_ = input.readInt32();
            break;
          }
          case 34: {
            org.spin.grpc.util.ClientRequest.Builder subBuilder = null;
            if (clientRequest_ != null) {
              subBuilder = clientRequest_.toBuilder();
            }
            clientRequest_ = input.readMessage(org.spin.grpc.util.ClientRequest.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(clientRequest_);
              clientRequest_ = subBuilder.buildPartial();
            }

            break;
          }
          case 42: {
            org.spin.grpc.util.Criteria.Builder subBuilder = null;
            if (criteria_ != null) {
              subBuilder = criteria_.toBuilder();
            }
            criteria_ = input.readMessage(org.spin.grpc.util.Criteria.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(criteria_);
              criteria_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.spin.grpc.util.ADempiereData.internal_static_data_GetEntityRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.spin.grpc.util.ADempiereData.internal_static_data_GetEntityRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.spin.grpc.util.GetEntityRequest.class, org.spin.grpc.util.GetEntityRequest.Builder.class);
  }

  public static final int UUID_FIELD_NUMBER = 1;
  private volatile java.lang.Object uuid_;
  /**
   * <code>string uuid = 1;</code>
   */
  public java.lang.String getUuid() {
    java.lang.Object ref = uuid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uuid_ = s;
      return s;
    }
  }
  /**
   * <code>string uuid = 1;</code>
   */
  public com.google.protobuf.ByteString
      getUuidBytes() {
    java.lang.Object ref = uuid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uuid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TABLEID_FIELD_NUMBER = 2;
  private int tableId_;
  /**
   * <code>int32 tableId = 2;</code>
   */
  public int getTableId() {
    return tableId_;
  }

  public static final int RECORDID_FIELD_NUMBER = 3;
  private int recordId_;
  /**
   * <code>int32 recordId = 3;</code>
   */
  public int getRecordId() {
    return recordId_;
  }

  public static final int CLIENTREQUEST_FIELD_NUMBER = 4;
  private org.spin.grpc.util.ClientRequest clientRequest_;
  /**
   * <code>.data.ClientRequest clientRequest = 4;</code>
   */
  public boolean hasClientRequest() {
    return clientRequest_ != null;
  }
  /**
   * <code>.data.ClientRequest clientRequest = 4;</code>
   */
  public org.spin.grpc.util.ClientRequest getClientRequest() {
    return clientRequest_ == null ? org.spin.grpc.util.ClientRequest.getDefaultInstance() : clientRequest_;
  }
  /**
   * <code>.data.ClientRequest clientRequest = 4;</code>
   */
  public org.spin.grpc.util.ClientRequestOrBuilder getClientRequestOrBuilder() {
    return getClientRequest();
  }

  public static final int CRITERIA_FIELD_NUMBER = 5;
  private org.spin.grpc.util.Criteria criteria_;
  /**
   * <pre>
   * Query
   * </pre>
   *
   * <code>.data.Criteria criteria = 5;</code>
   */
  public boolean hasCriteria() {
    return criteria_ != null;
  }
  /**
   * <pre>
   * Query
   * </pre>
   *
   * <code>.data.Criteria criteria = 5;</code>
   */
  public org.spin.grpc.util.Criteria getCriteria() {
    return criteria_ == null ? org.spin.grpc.util.Criteria.getDefaultInstance() : criteria_;
  }
  /**
   * <pre>
   * Query
   * </pre>
   *
   * <code>.data.Criteria criteria = 5;</code>
   */
  public org.spin.grpc.util.CriteriaOrBuilder getCriteriaOrBuilder() {
    return getCriteria();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getUuidBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, uuid_);
    }
    if (tableId_ != 0) {
      output.writeInt32(2, tableId_);
    }
    if (recordId_ != 0) {
      output.writeInt32(3, recordId_);
    }
    if (clientRequest_ != null) {
      output.writeMessage(4, getClientRequest());
    }
    if (criteria_ != null) {
      output.writeMessage(5, getCriteria());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getUuidBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, uuid_);
    }
    if (tableId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, tableId_);
    }
    if (recordId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, recordId_);
    }
    if (clientRequest_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getClientRequest());
    }
    if (criteria_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getCriteria());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.spin.grpc.util.GetEntityRequest)) {
      return super.equals(obj);
    }
    org.spin.grpc.util.GetEntityRequest other = (org.spin.grpc.util.GetEntityRequest) obj;

    boolean result = true;
    result = result && getUuid()
        .equals(other.getUuid());
    result = result && (getTableId()
        == other.getTableId());
    result = result && (getRecordId()
        == other.getRecordId());
    result = result && (hasClientRequest() == other.hasClientRequest());
    if (hasClientRequest()) {
      result = result && getClientRequest()
          .equals(other.getClientRequest());
    }
    result = result && (hasCriteria() == other.hasCriteria());
    if (hasCriteria()) {
      result = result && getCriteria()
          .equals(other.getCriteria());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + UUID_FIELD_NUMBER;
    hash = (53 * hash) + getUuid().hashCode();
    hash = (37 * hash) + TABLEID_FIELD_NUMBER;
    hash = (53 * hash) + getTableId();
    hash = (37 * hash) + RECORDID_FIELD_NUMBER;
    hash = (53 * hash) + getRecordId();
    if (hasClientRequest()) {
      hash = (37 * hash) + CLIENTREQUEST_FIELD_NUMBER;
      hash = (53 * hash) + getClientRequest().hashCode();
    }
    if (hasCriteria()) {
      hash = (37 * hash) + CRITERIA_FIELD_NUMBER;
      hash = (53 * hash) + getCriteria().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.spin.grpc.util.GetEntityRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.GetEntityRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.spin.grpc.util.GetEntityRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.spin.grpc.util.GetEntityRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Get Entity Request
   * </pre>
   *
   * Protobuf type {@code data.GetEntityRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:data.GetEntityRequest)
      org.spin.grpc.util.GetEntityRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.spin.grpc.util.ADempiereData.internal_static_data_GetEntityRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.spin.grpc.util.ADempiereData.internal_static_data_GetEntityRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.spin.grpc.util.GetEntityRequest.class, org.spin.grpc.util.GetEntityRequest.Builder.class);
    }

    // Construct using org.spin.grpc.util.GetEntityRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      uuid_ = "";

      tableId_ = 0;

      recordId_ = 0;

      if (clientRequestBuilder_ == null) {
        clientRequest_ = null;
      } else {
        clientRequest_ = null;
        clientRequestBuilder_ = null;
      }
      if (criteriaBuilder_ == null) {
        criteria_ = null;
      } else {
        criteria_ = null;
        criteriaBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.spin.grpc.util.ADempiereData.internal_static_data_GetEntityRequest_descriptor;
    }

    public org.spin.grpc.util.GetEntityRequest getDefaultInstanceForType() {
      return org.spin.grpc.util.GetEntityRequest.getDefaultInstance();
    }

    public org.spin.grpc.util.GetEntityRequest build() {
      org.spin.grpc.util.GetEntityRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.spin.grpc.util.GetEntityRequest buildPartial() {
      org.spin.grpc.util.GetEntityRequest result = new org.spin.grpc.util.GetEntityRequest(this);
      result.uuid_ = uuid_;
      result.tableId_ = tableId_;
      result.recordId_ = recordId_;
      if (clientRequestBuilder_ == null) {
        result.clientRequest_ = clientRequest_;
      } else {
        result.clientRequest_ = clientRequestBuilder_.build();
      }
      if (criteriaBuilder_ == null) {
        result.criteria_ = criteria_;
      } else {
        result.criteria_ = criteriaBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.spin.grpc.util.GetEntityRequest) {
        return mergeFrom((org.spin.grpc.util.GetEntityRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.spin.grpc.util.GetEntityRequest other) {
      if (other == org.spin.grpc.util.GetEntityRequest.getDefaultInstance()) return this;
      if (!other.getUuid().isEmpty()) {
        uuid_ = other.uuid_;
        onChanged();
      }
      if (other.getTableId() != 0) {
        setTableId(other.getTableId());
      }
      if (other.getRecordId() != 0) {
        setRecordId(other.getRecordId());
      }
      if (other.hasClientRequest()) {
        mergeClientRequest(other.getClientRequest());
      }
      if (other.hasCriteria()) {
        mergeCriteria(other.getCriteria());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.spin.grpc.util.GetEntityRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.spin.grpc.util.GetEntityRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object uuid_ = "";
    /**
     * <code>string uuid = 1;</code>
     */
    public java.lang.String getUuid() {
      java.lang.Object ref = uuid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uuid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string uuid = 1;</code>
     */
    public com.google.protobuf.ByteString
        getUuidBytes() {
      java.lang.Object ref = uuid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uuid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string uuid = 1;</code>
     */
    public Builder setUuid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uuid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string uuid = 1;</code>
     */
    public Builder clearUuid() {
      
      uuid_ = getDefaultInstance().getUuid();
      onChanged();
      return this;
    }
    /**
     * <code>string uuid = 1;</code>
     */
    public Builder setUuidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uuid_ = value;
      onChanged();
      return this;
    }

    private int tableId_ ;
    /**
     * <code>int32 tableId = 2;</code>
     */
    public int getTableId() {
      return tableId_;
    }
    /**
     * <code>int32 tableId = 2;</code>
     */
    public Builder setTableId(int value) {
      
      tableId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 tableId = 2;</code>
     */
    public Builder clearTableId() {
      
      tableId_ = 0;
      onChanged();
      return this;
    }

    private int recordId_ ;
    /**
     * <code>int32 recordId = 3;</code>
     */
    public int getRecordId() {
      return recordId_;
    }
    /**
     * <code>int32 recordId = 3;</code>
     */
    public Builder setRecordId(int value) {
      
      recordId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 recordId = 3;</code>
     */
    public Builder clearRecordId() {
      
      recordId_ = 0;
      onChanged();
      return this;
    }

    private org.spin.grpc.util.ClientRequest clientRequest_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.ClientRequest, org.spin.grpc.util.ClientRequest.Builder, org.spin.grpc.util.ClientRequestOrBuilder> clientRequestBuilder_;
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public boolean hasClientRequest() {
      return clientRequestBuilder_ != null || clientRequest_ != null;
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public org.spin.grpc.util.ClientRequest getClientRequest() {
      if (clientRequestBuilder_ == null) {
        return clientRequest_ == null ? org.spin.grpc.util.ClientRequest.getDefaultInstance() : clientRequest_;
      } else {
        return clientRequestBuilder_.getMessage();
      }
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public Builder setClientRequest(org.spin.grpc.util.ClientRequest value) {
      if (clientRequestBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        clientRequest_ = value;
        onChanged();
      } else {
        clientRequestBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public Builder setClientRequest(
        org.spin.grpc.util.ClientRequest.Builder builderForValue) {
      if (clientRequestBuilder_ == null) {
        clientRequest_ = builderForValue.build();
        onChanged();
      } else {
        clientRequestBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public Builder mergeClientRequest(org.spin.grpc.util.ClientRequest value) {
      if (clientRequestBuilder_ == null) {
        if (clientRequest_ != null) {
          clientRequest_ =
            org.spin.grpc.util.ClientRequest.newBuilder(clientRequest_).mergeFrom(value).buildPartial();
        } else {
          clientRequest_ = value;
        }
        onChanged();
      } else {
        clientRequestBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public Builder clearClientRequest() {
      if (clientRequestBuilder_ == null) {
        clientRequest_ = null;
        onChanged();
      } else {
        clientRequest_ = null;
        clientRequestBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public org.spin.grpc.util.ClientRequest.Builder getClientRequestBuilder() {
      
      onChanged();
      return getClientRequestFieldBuilder().getBuilder();
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    public org.spin.grpc.util.ClientRequestOrBuilder getClientRequestOrBuilder() {
      if (clientRequestBuilder_ != null) {
        return clientRequestBuilder_.getMessageOrBuilder();
      } else {
        return clientRequest_ == null ?
            org.spin.grpc.util.ClientRequest.getDefaultInstance() : clientRequest_;
      }
    }
    /**
     * <code>.data.ClientRequest clientRequest = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.ClientRequest, org.spin.grpc.util.ClientRequest.Builder, org.spin.grpc.util.ClientRequestOrBuilder> 
        getClientRequestFieldBuilder() {
      if (clientRequestBuilder_ == null) {
        clientRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.spin.grpc.util.ClientRequest, org.spin.grpc.util.ClientRequest.Builder, org.spin.grpc.util.ClientRequestOrBuilder>(
                getClientRequest(),
                getParentForChildren(),
                isClean());
        clientRequest_ = null;
      }
      return clientRequestBuilder_;
    }

    private org.spin.grpc.util.Criteria criteria_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.Criteria, org.spin.grpc.util.Criteria.Builder, org.spin.grpc.util.CriteriaOrBuilder> criteriaBuilder_;
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public boolean hasCriteria() {
      return criteriaBuilder_ != null || criteria_ != null;
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public org.spin.grpc.util.Criteria getCriteria() {
      if (criteriaBuilder_ == null) {
        return criteria_ == null ? org.spin.grpc.util.Criteria.getDefaultInstance() : criteria_;
      } else {
        return criteriaBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public Builder setCriteria(org.spin.grpc.util.Criteria value) {
      if (criteriaBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        criteria_ = value;
        onChanged();
      } else {
        criteriaBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public Builder setCriteria(
        org.spin.grpc.util.Criteria.Builder builderForValue) {
      if (criteriaBuilder_ == null) {
        criteria_ = builderForValue.build();
        onChanged();
      } else {
        criteriaBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public Builder mergeCriteria(org.spin.grpc.util.Criteria value) {
      if (criteriaBuilder_ == null) {
        if (criteria_ != null) {
          criteria_ =
            org.spin.grpc.util.Criteria.newBuilder(criteria_).mergeFrom(value).buildPartial();
        } else {
          criteria_ = value;
        }
        onChanged();
      } else {
        criteriaBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public Builder clearCriteria() {
      if (criteriaBuilder_ == null) {
        criteria_ = null;
        onChanged();
      } else {
        criteria_ = null;
        criteriaBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public org.spin.grpc.util.Criteria.Builder getCriteriaBuilder() {
      
      onChanged();
      return getCriteriaFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    public org.spin.grpc.util.CriteriaOrBuilder getCriteriaOrBuilder() {
      if (criteriaBuilder_ != null) {
        return criteriaBuilder_.getMessageOrBuilder();
      } else {
        return criteria_ == null ?
            org.spin.grpc.util.Criteria.getDefaultInstance() : criteria_;
      }
    }
    /**
     * <pre>
     * Query
     * </pre>
     *
     * <code>.data.Criteria criteria = 5;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        org.spin.grpc.util.Criteria, org.spin.grpc.util.Criteria.Builder, org.spin.grpc.util.CriteriaOrBuilder> 
        getCriteriaFieldBuilder() {
      if (criteriaBuilder_ == null) {
        criteriaBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            org.spin.grpc.util.Criteria, org.spin.grpc.util.Criteria.Builder, org.spin.grpc.util.CriteriaOrBuilder>(
                getCriteria(),
                getParentForChildren(),
                isClean());
        criteria_ = null;
      }
      return criteriaBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:data.GetEntityRequest)
  }

  // @@protoc_insertion_point(class_scope:data.GetEntityRequest)
  private static final org.spin.grpc.util.GetEntityRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.spin.grpc.util.GetEntityRequest();
  }

  public static org.spin.grpc.util.GetEntityRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetEntityRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetEntityRequest>() {
    public GetEntityRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetEntityRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetEntityRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetEntityRequest> getParserForType() {
    return PARSER;
  }

  public org.spin.grpc.util.GetEntityRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

