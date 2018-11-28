/**
 * DB.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package db;

public interface DB extends java.rmi.Remote {
    public boolean saveUser(beans.User userToSave) throws java.rmi.RemoteException;
    public beans.User[] getAllUsers() throws java.rmi.RemoteException;
}
