package estrutura;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.Unmarshaller;
import javax.xml.rpc.ServiceException;

import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.NameValuePair;
import org.omg.CORBA.Request;

import com.sun.mail.iap.ResponseInputStream;

import beans.User;
import db.DBServiceLocator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_last_name;
	private JTextField tf_email;
	private JTextField tf_phone;
	private static int HTTP_COD_SUCESSO = 200;
	private final String USER_AGENT = "Mozilla/5.0";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ServiceException 
	 * @throws RemoteException 
	 */
	public TelaPrincipal() throws ServiceException, RemoteException {
		setTitle("::.. Cadastro de usu\u00E1rio ..::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCadastro = new JLabel("Cadastro de Usu\u00E1rios");
		lblCadastro.setBounds(126, 11, 147, 14);
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCadastro);
		
		JLabel name = new JLabel("Nome");
		name.setBounds(10, 53, 97, 14);
		panel.add(name);
		
		tf_name = new JTextField();
		tf_name.setBounds(117, 50, 264, 20);
		panel.add(tf_name);
		tf_name.setColumns(10);
		
		JLabel last_name = new JLabel("Sobrenome");
		last_name.setBounds(10, 79, 97, 14);
		panel.add(last_name);
		
		tf_last_name = new JTextField();
		tf_last_name.setBounds(117, 76, 264, 20);
		panel.add(tf_last_name);
		tf_last_name.setColumns(10);
		
		JLabel email = new JLabel("Email");
		email.setBounds(10, 105, 82, 14);
		panel.add(email);
		
		tf_email = new JTextField();
		tf_email.setBounds(117, 102, 264, 20);
		panel.add(tf_email);
		tf_email.setColumns(10);
		
		JLabel phone = new JLabel("Telefone");
		phone.setBounds(10, 131, 91, 14);
		panel.add(phone);
		
		tf_phone = new JTextField();
		tf_phone.setBounds(117, 128, 264, 20);
		panel.add(tf_phone);
		tf_phone.setColumns(10);
		
		JButton btn_listar = new JButton("Listar");
		btn_listar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	 
		        try {
		 
		            URL url = new URL("https://apicadastro.herokuapp.com/api/users");
		            HttpURLConnection con = (HttpURLConnection) url.openConnection();
		 
		            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
		                throw new RuntimeException("HTTP error code : "+ con.getResponseCode());
		            }
		 
		            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));			            
		            StringBuilder sb = new StringBuilder();	
		            
		            String line;
		            while ((line = br.readLine()) != null) {
		            	sb.append(line);
		            }				           
		            
		            String[] exploded = sb.toString().replace("[", "").replace("]", "").split("},");
		            ArrayList<JSONObject> arrays = new ArrayList<JSONObject>();
		            for (int i = 0; i < exploded.length; i++) {
		            	if(i != exploded.length-1) {
							arrays.add(new JSONObject(exploded[i]+"}"));
		            	}else {
		            		arrays.add(new JSONObject(exploded[i]));
		            	}							
					}
		            
		            Frame frame = new Frame();
					String result = "";
					if(arrays.size() == 0) {
						result = "Não possuem usuários registrados!";
						JOptionPane.showMessageDialog(frame, result, "::.. Usuários Cadastrados ..::", 1);
					}else {
						for (int i = 0; i < arrays.size(); i++) {
							JSONObject object = arrays.get(i);
							result += "Nome: "+object.getString("first_name")+" "+object.getString("last_name")+" | Telefone: "+object.getString("phone")+" | Email: "+object.getString("email")+"\n\n";
						}
						JOptionPane.showMessageDialog(frame, result, "::.. Usuários Cadastrados ..::", 1);
					}	
		            
		            con.disconnect();
		 
		        } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}     
			}
		});
	
		btn_listar.setBounds(50, 179, 91, 23);
		panel.add(btn_listar);
		
		JButton btn_cadastrar = new JButton("Cadastrar");
		btn_cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String url = "https://apicadastro.herokuapp.com/api/users";
				URL obj = null;
				try {
					obj = new URL(url);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpsURLConnection con = null;
				try {
					con = (HttpsURLConnection) obj.openConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				//add reuqest header
				try {
					con.setRequestMethod("POST");
				} catch (ProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				con.setRequestProperty("User-Agent", USER_AGENT);

				String urlParameters = "first_name="+tf_name.getText().toString()+"&last_name="+tf_last_name.getText().toString()+"&email="+tf_email.getText().toString()+"&phone="+tf_phone.getText().toString()+"";
				
				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = null;
				try {
					wr = new DataOutputStream(con.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					wr.writeBytes(urlParameters);

					wr.flush();
					wr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				int responseCode = 0;
				try {
					responseCode = con.getResponseCode();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				BufferedReader in = null;
				try {
					in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String inputLine;
				StringBuffer response = new StringBuffer();

				try {
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//print result
				JSONObject resposta = null;
				try {
					resposta = new JSONObject(response.toString());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					int resultado = (int) resposta.get("result");
					Frame frame = new Frame();
					if(resultado == 1) {
						JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!", "::.. Cadastrado ..::", 1);
					}else {
						JOptionPane.showMessageDialog(frame, "Não foi possível adicionar o usuário.", "::.. Falha ..::", 1);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btn_cadastrar.setBounds(235, 179, 97, 23);
		panel.add(btn_cadastrar);
	}

}
