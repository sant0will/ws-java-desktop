package estrutura;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Main {
	public static void main(String[] args) throws ServiceException {
		try {
			TelaPrincipal tp = new TelaPrincipal();
			tp.setVisible(true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	private void init() throws ServiceException {
//		DBServiceLocator locator = new DBServiceLocator();
//		System.out.println(locator);
//		try {
//			db.DB ws = locator.getDB();
//			beans.User[] allUsers = ws.getAllUsers();
//			System.out.println(allUsers);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//			
////				User[] lista = ws.getAllUsers();
////				System.out.println(lista);
//		
//		
//	}
}
