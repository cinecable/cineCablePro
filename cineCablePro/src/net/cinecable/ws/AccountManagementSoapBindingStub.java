/**
 * AccountManagementSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.cinecable.ws;

public class AccountManagementSoapBindingStub extends org.apache.axis.client.Stub implements net.cinecable.ws.AccountManagement {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[32];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIspById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ISP"));
        oper.setReturnClass(net.cinecable.ws.ISP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIspByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getIspByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ispName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ISP"));
        oper.setReturnClass(net.cinecable.ws.ISP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getIspByNameReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMyIsps");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfISP"));
        oper.setReturnClass(net.cinecable.ws.ISP[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getMyIspsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDwellingTypes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOf_xsd_string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getAllDwellingTypesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "acc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"), net.cinecable.ws.Account.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"));
        oper.setReturnClass(net.cinecable.ws.Account.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "createAccountReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAccountById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "acc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"), net.cinecable.ws.Account.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateAccountByNumber");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "acc"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"), net.cinecable.ws.Account.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeAccountServiceableAddress");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "externalAccountID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("lockDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unlockDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccountByNumber");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"));
        oper.setReturnClass(net.cinecable.ws.Account.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getAccountByNumberReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAccountById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account"));
        oper.setReturnClass(net.cinecable.ws.Account.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getAccountByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchAccountsByExternalAccountId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "externalAccountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfAccount"));
        oper.setReturnClass(net.cinecable.ws.Account[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "searchAccountsByExternalAccountIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activateAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deactivateAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addDeviceToAccountById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "device"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"), net.cinecable.ws.Device.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addDeviceToAccountByNumber");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "device"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"), net.cinecable.ws.Device.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"));
        oper.setReturnClass(net.cinecable.ws.Device.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDeviceByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevicesForAccountById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfDevice"));
        oper.setReturnClass(net.cinecable.ws.Device[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDevicesForAccountByIdReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDevicesForAccountByNumber");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfDevice"));
        oper.setReturnClass(net.cinecable.ws.Device[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDevicesForAccountByNumberReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDeviceByMac");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"));
        oper.setReturnClass(net.cinecable.ws.Device.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getDeviceByMacReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "device"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device"), net.cinecable.ws.Device.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteDeviceFromAccountByMac");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceMac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteDeviceFromAccountById");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "deviceId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "accountId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"), java.lang.Integer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("serviceableAddressSearch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "params"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceableSearchParams"), net.cinecable.ws.ServiceableSearchParams.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfServiceableAddressResult"));
        oper.setReturnClass(net.cinecable.ws.ServiceableAddressResult[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "serviceableAddressSearchReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activateDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deactivateDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "changeComment"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDeviceMibObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfWsMibObject"));
        oper.setReturnClass(net.cinecable.ws.WsMibObject[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "getAllDeviceMibObjectsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addDeviceMibObject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mibObject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WsMibObject"), net.cinecable.ws.WsMibObject.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteAllDeviceMibObjects");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteDeviceMibObject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "oid"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateDeviceMibObject");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "macAddress"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mibObject"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WsMibObject"), net.cinecable.ws.WsMibObject.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "initiator"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User"), net.cinecable.ws.User.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "fault"),
                      "com.promptlink.www.xsd.WebServiceException",
                      new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException"), 
                      true
                     ));
        _operations[31] = oper;

    }

    public AccountManagementSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AccountManagementSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AccountManagementSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.Account.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOf_xsd_string");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfAccount");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.Account[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Account");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfDevice");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.Device[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfISP");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.ISP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ISP");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfServiceableAddressResult");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.ServiceableAddressResult[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceableAddressResult");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ArrayOfWsMibObject");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.WsMibObject[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WsMibObject");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "Device");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.Device.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ISP");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.ISP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceableAddressResult");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.ServiceableAddressResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "ServiceableSearchParams");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.ServiceableSearchParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "User");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.User.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WebServiceException");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.WebServiceException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "WsMibObject");
            cachedSerQNames.add(qName);
            cls = net.cinecable.ws.WsMibObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public net.cinecable.ws.ISP getIspById(java.lang.Integer id, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getIspById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {id, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.ISP) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.ISP) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.ISP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.ISP getIspByName(java.lang.String ispName, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getIspByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ispName, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.ISP) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.ISP) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.ISP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.ISP[] getMyIsps(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getMyIsps"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.ISP[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.ISP[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.ISP[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String[] getAllDwellingTypes(net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getAllDwellingTypes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Account createAccount(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "createAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {acc, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Account) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Account) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Account.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateAccountById(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "updateAccountById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {acc, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateAccountByNumber(net.cinecable.ws.Account acc, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "updateAccountByNumber"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {acc, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void changeAccountServiceableAddress(java.lang.String accountNumber, java.lang.String externalAccountID, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "changeAccountServiceableAddress"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, externalAccountID, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void lockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "lockDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {mac, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void unlockDevice(java.lang.String mac, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "unlockDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {mac, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Account getAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getAccountByNumber"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Account) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Account) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Account.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Account getAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getAccountById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountId, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Account) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Account) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Account.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Account[] searchAccountsByExternalAccountId(java.lang.String externalAccountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "searchAccountsByExternalAccountId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {externalAccountId, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Account[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Account[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Account[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void activateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "activateAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deactivateAccount(java.lang.String accountNumber, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deactivateAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void addDeviceToAccountById(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "addDeviceToAccountById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {device, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void addDeviceToAccountByNumber(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "addDeviceToAccountByNumber"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {device, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Device getDeviceById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getDeviceById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deviceId, accountId, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Device) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Device) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Device.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Device[] getDevicesForAccountById(java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getDevicesForAccountById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountId, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Device[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Device[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Device[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Device[] getDevicesForAccountByNumber(java.lang.String accountNumber, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getDevicesForAccountByNumber"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Device[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Device[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Device[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.Device getDeviceByMac(java.lang.String mac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getDeviceByMac"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {mac, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.Device) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.Device) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.Device.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateDevice(net.cinecable.ws.Device device, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "updateDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {device, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deleteDeviceFromAccountByMac(java.lang.String accountNumber, java.lang.String deviceMac, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deleteDeviceFromAccountByMac"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {accountNumber, deviceMac, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deleteDeviceFromAccountById(java.lang.Integer deviceId, java.lang.Integer accountId, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deleteDeviceFromAccountById"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deviceId, accountId, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.ServiceableAddressResult[] serviceableAddressSearch(net.cinecable.ws.ServiceableSearchParams params, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "serviceableAddressSearch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {params, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.ServiceableAddressResult[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.ServiceableAddressResult[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.ServiceableAddressResult[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void activateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "activateDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deactivateDevice(java.lang.String macAddress, java.lang.String changeComment, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deactivateDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, changeComment, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public net.cinecable.ws.WsMibObject[] getAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "getAllDeviceMibObjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (net.cinecable.ws.WsMibObject[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (net.cinecable.ws.WsMibObject[]) org.apache.axis.utils.JavaUtils.convert(_resp, net.cinecable.ws.WsMibObject[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void addDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "addDeviceMibObject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, mibObject, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deleteAllDeviceMibObjects(java.lang.String macAddress, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deleteAllDeviceMibObjects"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void deleteDeviceMibObject(java.lang.String macAddress, java.lang.String oid, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "deleteDeviceMibObject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, oid, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public void updateDeviceMibObject(java.lang.String macAddress, net.cinecable.ws.WsMibObject mibObject, net.cinecable.ws.User initiator) throws java.rmi.RemoteException, net.cinecable.ws.WebServiceException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://www.promptlink.com/xsd", "updateDeviceMibObject"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {macAddress, mibObject, initiator});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof net.cinecable.ws.WebServiceException) {
              throw (net.cinecable.ws.WebServiceException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
