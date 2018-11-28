package db;

public class DBProxy implements db.DB {
  private String _endpoint = null;
  private db.DB dB = null;
  
  public DBProxy() {
    _initDBProxy();
  }
  
  public DBProxy(String endpoint) {
    _endpoint = endpoint;
    _initDBProxy();
  }
  
  private void _initDBProxy() {
    try {
      dB = (new db.DBServiceLocator()).getDB();
      if (dB != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dB)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dB != null)
      ((javax.xml.rpc.Stub)dB)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public db.DB getDB() {
    if (dB == null)
      _initDBProxy();
    return dB;
  }
  
  public boolean saveUser(beans.User userToSave) throws java.rmi.RemoteException{
    if (dB == null)
      _initDBProxy();
    return dB.saveUser(userToSave);
  }
  
  public beans.User[] getAllUsers() throws java.rmi.RemoteException{
    if (dB == null)
      _initDBProxy();
    return dB.getAllUsers();
  }
  
  
}