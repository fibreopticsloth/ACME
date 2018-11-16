package acme;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import sun.net.www.http.HttpClient;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ACME {
	private static JTextField textField;

	public static void main(String[] args) {

		JFrame main = new JFrame();
		
		main.setSize(700, 600);
		main.getContentPane().setLayout(null);
		
		JLabel lblAcmeMovieSearch = new JLabel("ACME Movie Search");
		lblAcmeMovieSearch.setBounds(0, 175, 684, 63);
		lblAcmeMovieSearch.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
		lblAcmeMovieSearch.setHorizontalAlignment(SwingConstants.CENTER);
		main.getContentPane().add(lblAcmeMovieSearch);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		textField.setBounds(113, 249, 458, 63);
		main.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton(" Database 1");
		rdbtnNewRadioButton.setBounds(355, 332, 109, 23);
		main.getContentPane().add(rdbtnNewRadioButton);
		
		
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(" Database 2");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(263, 332, 109, 23);
		main.getContentPane().add(rdbtnNewRadioButton_1);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if (rdbtnNewRadioButton_1.isSelected()){
					
					HttpResponse<JsonNode> response = null;
			
						try {
							response = 
									Unirest.get("http://www.omdbapi.com/?apikey=9d6240b0&t="+textField.getText().replaceAll(" ", "+")).asJson();
						} catch (UnirestException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			
					System.out.println(response.getBody().getObject().toString(2));
					
				}
				
				else if (rdbtnNewRadioButton.isSelected()) {
					
					HttpResponse<JsonNode> response = null;
					try {
						response = 
								Unirest.get("https://api.themoviedb.org/3/search/movie?api_key=6495d23587949a47871fb82a6f4aa9cb&query="+textField.getText().replaceAll(" ", "%20")).asJson();
					} catch (UnirestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(response.getBody().getObject().toString(2));
					
				}
				
				
			}
			
		});
		btnNewButton.setBounds(214, 373, 256, 40);
		main.getContentPane().add(btnNewButton);
		
		main.setVisible(true);

	}
}
