package trabalho;

import java.util.ArrayList;
import java.util.*;

import javax.swing.JOptionPane;

public class Main {
// inserir os dados para a mochila(capacidade, quantidade, nome, peso e lucro de cada objeto da mochila)
	public static void main(String[] args) {
		
		Objeto objeto = null;
		int cont = 0;
		
		int mochila = Integer.parseInt(JOptionPane.showInputDialog("Digite a capacidade da mochila: "));
		
		ArrayList<Objeto> objetos = new ArrayList<>();
		
		cont = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de objetos: ")); 
		
		for (int i=0;i<cont;i++) {
			objeto = new Objeto();
			objeto.setNome(JOptionPane.showInputDialog("Digite o nome do objeto - "+(i+1)));
			objeto.setPeso(Integer.parseInt(JOptionPane.showInputDialog("Digite o peso do objeto -> "+objeto.getNome())));
			objeto.setLucro(Integer.parseInt(JOptionPane.showInputDialog("Digite o lucro do objeto ->"+objeto.getNome())));
			objetos.add(objeto);
		}		
		progDinamica(objetos, mochila);
		algoritmoGuloso(objetos, mochila);
		
	}
	// fazer a comparacao do maior valor do objeto
	public static Objeto maiorValor(ArrayList<Objeto> objetos) {
		
		double lucro = 0;
		Objeto objeto = null;
		
		for (Objeto o: objetos) {
			if (lucro <= (o.getLucro()/o.getPeso())) {
				lucro = o.getLucro()/o.getPeso();
				objeto = o;
			}
		}
		
		return objeto;
		
	}
	// o maior peso e valor da mochina do algoritmo guloso	
	public static void algoritmoGuloso(ArrayList<Objeto> objetos, int mochila) {
		
		int peso = 0;
		Objeto objeto = null;
		String itens = "-----Algoritmo Guloso-----\n\nOs seguintes itens serão selecionados:\n";
		
		while (peso<= mochila && !objetos.isEmpty()) {
			objeto = maiorValor(objetos);
			peso += objeto.getPeso();
			if (peso<=mochila) {
				itens += objeto.getNome()+"\n";
				objetos.remove(objeto);
			}
			else {
				peso -= objeto.getPeso();
				objetos.remove(objeto);
			}
		}
		
		JOptionPane.showMessageDialog(null, itens);
		
	}
	
	public static void progDinamica(ArrayList<Objeto> objetos, int mochila) {
		
		double matriz[][] = new double [objetos.size()+1][mochila+1];
		double valor = 0;
				
		for (int i=0; i<=objetos.size(); i++) {
			for (int j=0; j<=mochila; j++) {
				if (i==0 || j==0) {
					matriz[i][j] = 0;
				}
				else if (objetos.get(i-1).getPeso()>j) {
					matriz[i][j] = matriz[i-1][j];
				}
				else {
					if (objetos.get(i-1).getPeso()<=j) {
						valor = matriz[i-1][(int) ((int) j-objetos.get(i-1).getPeso())];
						matriz[i][j] = objetos.get(i-1).getLucro()+valor; 
					}
					if (matriz[i][j]<matriz[i-1][j]) {
						matriz[i][j]=matriz[i-1][j]; 
					}
				}
					
			}
		}
		
		int i = objetos.size();
		int j = mochila;
		String itens = "-----Programação Dinâmica-----\n\nOs seguintes itens serão selecionados:\n";
		while (i!=0) {
			if (matriz[i][j]!=matriz[i-1][j]) {
				itens += objetos.get(i-1).getNome()+"\n";
				j-=objetos.get(i-1).getPeso();
				i--;
			}
			else {
				i--;
			}
		}
		
		JOptionPane.showMessageDialog(null, itens);
		
	}

}
