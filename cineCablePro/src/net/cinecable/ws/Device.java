/**
 * Device.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws;

public class Device  implements java.io.Serializable {
    private int accountId;

    private java.lang.String accountNumber;

    private java.lang.String addressApartment;

    private java.lang.String addressBuilding;

    private java.lang.String addressCity;

    private java.lang.String addressCountry;

    private java.lang.String addressFraction;

    private boolean addressFromAccount;

    private java.lang.String addressHouseNumber;

    private java.lang.String addressLocation;

    private java.lang.String addressState;

    private java.lang.String addressStreet;

    private java.lang.String addressZip4;

    private java.lang.String addressZip5;

    private int deactivated;

    private java.lang.String description;

    private int deviceId;

    private int deviceType;

    private long installDate;

    private int ispId;

    private java.lang.String locked;

    private java.lang.String nodeId;

    private java.lang.String nodeLeg;

    private java.lang.String primaryMacAddress;

    private java.lang.String secondaryMacAddress;

    private java.lang.String serviceClass;
    

    public Device() {
    }

    public Device(
           int accountId,
           java.lang.String accountNumber,
           java.lang.String addressApartment,
           java.lang.String addressBuilding,
           java.lang.String addressCity,
           java.lang.String addressCountry,
           java.lang.String addressFraction,
           boolean addressFromAccount,
           java.lang.String addressHouseNumber,
           java.lang.String addressLocation,
           java.lang.String addressState,
           java.lang.String addressStreet,
           java.lang.String addressZip4,
           java.lang.String addressZip5,
           int deactivated,
           java.lang.String description,
           int deviceId,
           int deviceType,
           long installDate,
           int ispId,
           java.lang.String locked,
           java.lang.String nodeId,
           java.lang.String nodeLeg,
           java.lang.String primaryMacAddress,
           java.lang.String secondaryMacAddress,
           java.lang.String serviceClass) {
           this.accountId = accountId;
           this.accountNumber = accountNumber;
           this.addressApartment = addressApartment;
           this.addressBuilding = addressBuilding;
           this.addressCity = addressCity;
           this.addressCountry = addressCountry;
           this.addressFraction = addressFraction;
           this.addressFromAccount = addressFromAccount;
           this.addressHouseNumber = addressHouseNumber;
           this.addressLocation = addressLocation;
           this.addressState = addressState;
           this.addressStreet = addressStreet;
           this.addressZip4 = addressZip4;
           this.addressZip5 = addressZip5;
           this.deactivated = deactivated;
           this.description = description;
           this.deviceId = deviceId;
           this.deviceType = deviceType;
           this.installDate = installDate;
           this.ispId = ispId;
           this.locked = locked;
           this.nodeId = nodeId;
           this.nodeLeg = nodeLeg;
           this.primaryMacAddress = primaryMacAddress;
           this.secondaryMacAddress = secondaryMacAddress;
           this.serviceClass = serviceClass;
    }


    /**
     * Gets the accountId value for this Device.
     * 
     * @return accountId
     */
    public int getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this Device.
     * 
     * @param accountId
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    /**
     * Gets the accountNumber value for this Device.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this Device.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the addressApartment value for this Device.
     * 
     * @return addressApartment
     */
    public java.lang.String getAddressApartment() {
        return addressApartment;
    }


    /**
     * Sets the addressApartment value for this Device.
     * 
     * @param addressApartment
     */
    public void setAddressApartment(java.lang.String addressApartment) {
        this.addressApartment = addressApartment;
    }


    /**
     * Gets the addressBuilding value for this Device.
     * 
     * @return addressBuilding
     */
    public java.lang.String getAddressBuilding() {
        return addressBuilding;
    }


    /**
     * Sets the addressBuilding value for this Device.
     * 
     * @param addressBuilding
     */
    public void setAddressBuilding(java.lang.String addressBuilding) {
        this.addressBuilding = addressBuilding;
    }


    /**
     * Gets the addressCity value for this Device.
     * 
     * @return addressCity
     */
    public java.lang.String getAddressCity() {
        return addressCity;
    }


    /**
     * Sets the addressCity value for this Device.
     * 
     * @param addressCity
     */
    public void setAddressCity(java.lang.String addressCity) {
        this.addressCity = addressCity;
    }


    /**
     * Gets the addressCountry value for this Device.
     * 
     * @return addressCountry
     */
    public java.lang.String getAddressCountry() {
        return addressCountry;
    }


    /**
     * Sets the addressCountry value for this Device.
     * 
     * @param addressCountry
     */
    public void setAddressCountry(java.lang.String addressCountry) {
        this.addressCountry = addressCountry;
    }


    /**
     * Gets the addressFraction value for this Device.
     * 
     * @return addressFraction
     */
    public java.lang.String getAddressFraction() {
        return addressFraction;
    }


    /**
     * Sets the addressFraction value for this Device.
     * 
     * @param addressFraction
     */
    public void setAddressFraction(java.lang.String addressFraction) {
        this.addressFraction = addressFraction;
    }


    /**
     * Gets the addressFromAccount value for this Device.
     * 
     * @return addressFromAccount
     */
    public boolean isAddressFromAccount() {
        return addressFromAccount;
    }


    /**
     * Sets the addressFromAccount value for this Device.
     * 
     * @param addressFromAccount
     */
    public void setAddressFromAccount(boolean addressFromAccount) {
        this.addressFromAccount = addressFromAccount;
    }


    /**
     * Gets the addressHouseNumber value for this Device.
     * 
     * @return addressHouseNumber
     */
    public java.lang.String getAddressHouseNumber() {
        return addressHouseNumber;
    }


    /**
     * Sets the addressHouseNumber value for this Device.
     * 
     * @param addressHouseNumber
     */
    public void setAddressHouseNumber(java.lang.String addressHouseNumber) {
        this.addressHouseNumber = addressHouseNumber;
    }


    /**
     * Gets the addressLocation value for this Device.
     * 
     * @return addressLocation
     */
    public java.lang.String getAddressLocation() {
        return addressLocation;
    }


    /**
     * Sets the addressLocation value for this Device.
     * 
     * @param addressLocation
     */
    public void setAddressLocation(java.lang.String addressLocation) {
        this.addressLocation = addressLocation;
    }


    /**
     * Gets the addressState value for this Device.
     * 
     * @return addressState
     */
    public java.lang.String getAddressState() {
        return addressState;
    }


    /**
     * Sets the addressState value for this Device.
     * 
     * @param addressState
     */
    public void setAddressState(java.lang.String addressState) {
        this.addressState = addressState;
    }


    /**
     * Gets the addressStreet value for this Device.
     * 
     * @return addressStreet
     */
    public java.lang.String getAddressStreet() {
        return addressStreet;
    }


    /**
     * Sets the addressStreet value for this Device.
     * 
     * @param addressStreet
     */
    public void setAddressStreet(java.lang.String addressStreet) {
        this.addressStreet = addressStreet;
    }


    /**
     * Gets the addressZip4 value for this Device.
     * 
     * @return addressZip4
     */
    public java.lang.String getAddressZip4() {
        return addressZip4;
    }


    /**
     * Sets the addressZip4 value for this Device.
     * 
     * @param addressZip4
     */
    public void setAddressZip4(java.lang.String addressZip4) {
        this.addressZip4 = addressZip4;
    }


    /**
     * Gets the addressZip5 value for this Device.
     * 
     * @return addressZip5
     */
    public java.lang.String getAddressZip5() {
        return addressZip5;
    }


    /**
     * Sets the addressZip5 value for this Device.
     * 
     * @param addressZip5
     */
    public void setAddressZip5(java.lang.String addressZip5) {
        this.addressZip5 = addressZip5;
    }


    /**
     * Gets the deactivated value for this Device.
     * 
     * @return deactivated
     */
    public int getDeactivated() {
        return deactivated;
    }


    /**
     * Sets the deactivated value for this Device.
     * 
     * @param deactivated
     */
    public void setDeactivated(int deactivated) {
        this.deactivated = deactivated;
    }


    /**
     * Gets the description value for this Device.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Device.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the deviceId value for this Device.
     * 
     * @return deviceId
     */
    public int getDeviceId() {
        return deviceId;
    }


    /**
     * Sets the deviceId value for this Device.
     * 
     * @param deviceId
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }


    /**
     * Gets the deviceType value for this Device.
     * 
     * @return deviceType
     */
    public int getDeviceType() {
        return deviceType;
    }


    /**
     * Sets the deviceType value for this Device.
     * 
     * @param deviceType
     */
    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }


    /**
     * Gets the installDate value for this Device.
     * 
     * @return installDate
     */
    public long getInstallDate() {
        return installDate;
    }


    /**
     * Sets the installDate value for this Device.
     * 
     * @param installDate
     */
    public void setInstallDate(long installDate) {
        this.installDate = installDate;
    }


    /**
     * Gets the ispId value for this Device.
     * 
     * @return ispId
     */
    public int getIspId() {
        return ispId;
    }


    /**
     * Sets the ispId value for this Device.
     * 
     * @param ispId
     */
    public void setIspId(int ispId) {
        this.ispId = ispId;
    }


    /**
     * Gets the locked value for this Device.
     * 
     * @return locked
     */
    public java.lang.String getLocked() {
        return locked;
    }


    /**
     * Sets the locked value for this Device.
     * 
     * @param locked
     */
    public void setLocked(java.lang.String locked) {
        this.locked = locked;
    }


    /**
     * Gets the nodeId value for this Device.
     * 
     * @return nodeId
     */
    public java.lang.String getNodeId() {
        return nodeId;
    }


    /**
     * Sets the nodeId value for this Device.
     * 
     * @param nodeId
     */
    public void setNodeId(java.lang.String nodeId) {
        this.nodeId = nodeId;
    }


    /**
     * Gets the nodeLeg value for this Device.
     * 
     * @return nodeLeg
     */
    public java.lang.String getNodeLeg() {
        return nodeLeg;
    }


    /**
     * Sets the nodeLeg value for this Device.
     * 
     * @param nodeLeg
     */
    public void setNodeLeg(java.lang.String nodeLeg) {
        this.nodeLeg = nodeLeg;
    }


    /**
     * Gets the primaryMacAddress value for this Device.
     * 
     * @return primaryMacAddress
     */
    public java.lang.String getPrimaryMacAddress() {
        return primaryMacAddress;
    }


    /**
     * Sets the primaryMacAddress value for this Device.
     * 
     * @param primaryMacAddress
     */
    public void setPrimaryMacAddress(java.lang.String primaryMacAddress) {
        this.primaryMacAddress = primaryMacAddress;
    }


    /**
     * Gets the secondaryMacAddress value for this Device.
     * 
     * @return secondaryMacAddress
     */
    public java.lang.String getSecondaryMacAddress() {
        return secondaryMacAddress;
    }


    /**
     * Sets the secondaryMacAddress value for this Device.
     * 
     * @param secondaryMacAddress
     */
    public void setSecondaryMacAddress(java.lang.String secondaryMacAddress) {
        this.secondaryMacAddress = secondaryMacAddress;
    }


    /**
     * Gets the serviceClass value for this Device.
     * 
     * @return serviceClass
     */
    public java.lang.String getServiceClass() {
        return serviceClass;
    }


    /**
     * Sets the serviceClass value for this Device.
     * 
     * @param serviceClass
     */
    public void setServiceClass(java.lang.String serviceClass) {
        this.serviceClass = serviceClass;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Device)) return false;
        Device other = (Device) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.accountId == other.getAccountId() &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.addressApartment==null && other.getAddressApartment()==null) || 
             (this.addressApartment!=null &&
              this.addressApartment.equals(other.getAddressApartment()))) &&
            ((this.addressBuilding==null && other.getAddressBuilding()==null) || 
             (this.addressBuilding!=null &&
              this.addressBuilding.equals(other.getAddressBuilding()))) &&
            ((this.addressCity==null && other.getAddressCity()==null) || 
             (this.addressCity!=null &&
              this.addressCity.equals(other.getAddressCity()))) &&
            ((this.addressCountry==null && other.getAddressCountry()==null) || 
             (this.addressCountry!=null &&
              this.addressCountry.equals(other.getAddressCountry()))) &&
            ((this.addressFraction==null && other.getAddressFraction()==null) || 
             (this.addressFraction!=null &&
              this.addressFraction.equals(other.getAddressFraction()))) &&
            this.addressFromAccount == other.isAddressFromAccount() &&
            ((this.addressHouseNumber==null && other.getAddressHouseNumber()==null) || 
             (this.addressHouseNumber!=null &&
              this.addressHouseNumber.equals(other.getAddressHouseNumber()))) &&
            ((this.addressLocation==null && other.getAddressLocation()==null) || 
             (this.addressLocation!=null &&
              this.addressLocation.equals(other.getAddressLocation()))) &&
            ((this.addressState==null && other.getAddressState()==null) || 
             (this.addressState!=null &&
              this.addressState.equals(other.getAddressState()))) &&
            ((this.addressStreet==null && other.getAddressStreet()==null) || 
             (this.addressStreet!=null &&
              this.addressStreet.equals(other.getAddressStreet()))) &&
            ((this.addressZip4==null && other.getAddressZip4()==null) || 
             (this.addressZip4!=null &&
              this.addressZip4.equals(other.getAddressZip4()))) &&
            ((this.addressZip5==null && other.getAddressZip5()==null) || 
             (this.addressZip5!=null &&
              this.addressZip5.equals(other.getAddressZip5()))) &&
            this.deactivated == other.getDeactivated() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.deviceId == other.getDeviceId() &&
            this.deviceType == other.getDeviceType() &&
            this.installDate == other.getInstallDate() &&
            this.ispId == other.getIspId() &&
            ((this.locked==null && other.getLocked()==null) || 
             (this.locked!=null &&
              this.locked.equals(other.getLocked()))) &&
            ((this.nodeId==null && other.getNodeId()==null) || 
             (this.nodeId!=null &&
              this.nodeId.equals(other.getNodeId()))) &&
            ((this.nodeLeg==null && other.getNodeLeg()==null) || 
             (this.nodeLeg!=null &&
              this.nodeLeg.equals(other.getNodeLeg()))) &&
            ((this.primaryMacAddress==null && other.getPrimaryMacAddress()==null) || 
             (this.primaryMacAddress!=null &&
              this.primaryMacAddress.equals(other.getPrimaryMacAddress()))) &&
            ((this.secondaryMacAddress==null && other.getSecondaryMacAddress()==null) || 
             (this.secondaryMacAddress!=null &&
              this.secondaryMacAddress.equals(other.getSecondaryMacAddress()))) &&
            ((this.serviceClass==null && other.getServiceClass()==null) || 
             (this.serviceClass!=null &&
              this.serviceClass.equals(other.getServiceClass())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getAccountId();
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getAddressApartment() != null) {
            _hashCode += getAddressApartment().hashCode();
        }
        if (getAddressBuilding() != null) {
            _hashCode += getAddressBuilding().hashCode();
        }
        if (getAddressCity() != null) {
            _hashCode += getAddressCity().hashCode();
        }
        if (getAddressCountry() != null) {
            _hashCode += getAddressCountry().hashCode();
        }
        if (getAddressFraction() != null) {
            _hashCode += getAddressFraction().hashCode();
        }
        _hashCode += (isAddressFromAccount() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAddressHouseNumber() != null) {
            _hashCode += getAddressHouseNumber().hashCode();
        }
        if (getAddressLocation() != null) {
            _hashCode += getAddressLocation().hashCode();
        }
        if (getAddressState() != null) {
            _hashCode += getAddressState().hashCode();
        }
        if (getAddressStreet() != null) {
            _hashCode += getAddressStreet().hashCode();
        }
        if (getAddressZip4() != null) {
            _hashCode += getAddressZip4().hashCode();
        }
        if (getAddressZip5() != null) {
            _hashCode += getAddressZip5().hashCode();
        }
        _hashCode += getDeactivated();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getDeviceId();
        _hashCode += getDeviceType();
        _hashCode += new Long(getInstallDate()).hashCode();
        _hashCode += getIspId();
        if (getLocked() != null) {
            _hashCode += getLocked().hashCode();
        }
        if (getNodeId() != null) {
            _hashCode += getNodeId().hashCode();
        }
        if (getNodeLeg() != null) {
            _hashCode += getNodeLeg().hashCode();
        }
        if (getPrimaryMacAddress() != null) {
            _hashCode += getPrimaryMacAddress().hashCode();
        }
        if (getSecondaryMacAddress() != null) {
            _hashCode += getSecondaryMacAddress().hashCode();
        }
        if (getServiceClass() != null) {
            _hashCode += getServiceClass().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Device.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressApartment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressApartment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressBuilding");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressBuilding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressCountry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressCountry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressFraction");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressFraction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressFromAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressFromAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressHouseNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressHouseNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressStreet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressStreet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressZip4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressZip4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressZip5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addressZip5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deactivated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deactivated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deviceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deviceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("installDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "installDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ispId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ispId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locked");
        elemField.setXmlName(new javax.xml.namespace.QName("", "locked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nodeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeLeg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nodeLeg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryMacAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "primaryMacAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryMacAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "secondaryMacAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serviceClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
