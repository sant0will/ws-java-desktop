/**
 * DBServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package db;

public class DBServiceLocator extends org.apache.axis.client.Service implements db.DBService {

    public DBServiceLocator() {
    }


    public DBServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DBServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DB
    private java.lang.String DB_address = "http://localhost:8080/ProjetoWeb/services/DB";

    public java.lang.String getDBAddress() {
        return DB_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DBWSDDServiceName = "DB";

    public java.lang.String getDBWSDDServiceName() {
        return DBWSDDServiceName;
    }

    public void setDBWSDDServiceName(java.lang.String name) {
        DBWSDDServiceName = name;
    }

    public db.DB getDB() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DB_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDB(endpoint);
    }

    public db.DB getDB(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            db.DBSoapBindingStub _stub = new db.DBSoapBindingStub(portAddress, this);
            _stub.setPortName(getDBWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDBEndpointAddress(java.lang.String address) {
        DB_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (db.DB.class.isAssignableFrom(serviceEndpointInterface)) {
                db.DBSoapBindingStub _stub = new db.DBSoapBindingStub(new java.net.URL(DB_address), this);
                _stub.setPortName(getDBWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DB".equals(inputPortName)) {
            return getDB();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://db", "DBService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://db", "DB"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DB".equals(portName)) {
            setDBEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
