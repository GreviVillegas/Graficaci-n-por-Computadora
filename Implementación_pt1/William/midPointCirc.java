package William;

import java.util.Arrays;
import java.util.Scanner;

public class midPointCirc{
	private int ptX1, ptY1, r;
	private char[][] grid;
	
	public midPointCirc(int ptX, int ptY, int r){
		ptX1 = ptX;
		ptY1 = ptY;
		this.r = r;
		this.grid = new char[50][50];
		for(char[] row : grid){
			Arrays.fill(row,' ');
		}
		drawCirc();
	}
	
	private void drawCirc(){
	int x = 0;
	int y = r;
	double d = 5/4 -r;
	simetricPlot(x,y);
	while(y > x){
		x++;
		if(d < 0){
		d += 2*x+1;
		}else{
		y--;
		d += 2*(x-y)+1;
		}
		simetricPlot(x,y);
	}
	
	}
	private void simetricPlot(int x, int y){
		if(ptX1 + x < 50 && ptY1 + 1 < 50){
			grid[ptX1 + x][ptY1 + y] = '*';
			grid[ptX1 - x][ptY1 + y] = '*';
			grid[ptX1 + x][ptY1 - y] = '*';
			grid[ptX1 - x][ptY1 - y] = '*';
			grid[ptX1 + y][ptY1 + x] = '*';
			grid[ptX1 - y][ptY1 + x] = '*';
			grid[ptX1 + y][ptY1 - x] = '*';
			grid[ptX1 - y][ptY1 - x] = '*';
		}
	}
	
	public void printGrid() {
        for (char[] row : grid) {
            for (char c : row) {
            	
            	////
            	try {
           		Thread.sleep(5);
        	} catch (InterruptedException e) {
            		e.printStackTrace();
        	}
            	////
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a,b,c;
		System.out.println("Introduzca el centro y radio de la circunferencia ");
		System.out.println("x: ");
		a = sc.nextInt();
		System.out.println("y: ");
		b = sc.nextInt();
		System.out.println("radio: ");
		c = sc.nextInt();
		midPointCirc circ1 = new midPointCirc(a,b,c);
		circ1.printGrid();
	}
}
