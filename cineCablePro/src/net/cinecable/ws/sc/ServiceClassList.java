/**
 * ServiceClassList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws.sc;

public class ServiceClassList  implements java.io.Serializable {
    private java.lang.String[] docsisServiceClasses;

    private java.lang.String[] packetcableServiceClasses;

    public ServiceClassList() {
    }

    public ServiceClassList(
           java.lang.String[] docsisServiceClasses,
           java.lang.String[] packetcableServiceClasses) {
           this.docsisServiceClasses = docsisServiceClasses;
           this.packetcableServiceClasses = packetcableServiceClasses;
    }


    /**
     * Gets the docsisServiceClasses value for this ServiceClassList.
     * 
     * @return docsisServiceClasses
     */
    public java.lang.String[] getDocsisServiceClasses() {
        return docsisServiceClasses;
    }


    /**
     * Sets the docsisServiceClasses value for this ServiceClassList.
     * 
     * @param docsisServiceClasses
     */
    public void setDocsisServiceClasses(java.lang.String[] docsisServiceClasses) {
        this.docsisServiceClasses = docsisServiceClasses;
    }


    /**
     * Gets the packetcableServiceClasses value for this ServiceClassList.
     * 
     * @return packetcableServiceClasses
     */
    public java.lang.String[] getPacketcableServiceClasses() {
        return packetcableServiceClasses;
    }


    /**
     * Sets the packetcableServiceClasses value for this ServiceClassList.
     * 
     * @param packetcableServiceClasses
     */
    public void setPacketcableServiceClasses(java.lang.String[] packetcableServiceClasses) {
        this.packetcableServiceClasses = packetcableServiceClasses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceClassList)) return false;
        ServiceClassList other = (ServiceClassList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.docsisServiceClasses==null && other.getDocsisServiceClasses()==null) || 
             (this.docsisServiceClasses!=null &&
              java.util.Arrays.equals(this.docsisServiceClasses, other.getDocsisServiceClasses()))) &&
            ((this.packetcableServiceClasses==null && other.getPacketcableServiceClasses()==null) || 
             (this.packetcableServiceClasses!=null &&
              java.util.Arrays.equals(this.packetcableServiceClasses, other.getPacketcableServiceClasses())));
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
        if (getDocsisServiceClasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocsisServiceClasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocsisServiceClasses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPacketcableServiceClasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPacketcableServiceClasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPacketcableServiceClasses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceClassList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceClassList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docsisServiceClasses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docsisServiceClasses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packetcableServiceClasses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "packetcableServiceClasses"));
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
