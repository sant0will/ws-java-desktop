/**
 * DBService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package db;

public interface DBService extends javax.xml.rpc.Service {
    public java.lang.String getDBAddress();

    public db.DB getDB() throws javax.xml.rpc.ServiceException;

    public db.DB getDB(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
