//A program For Matrix Addition , Substraction and Multiplication

import java.util.Scanner;
class Matrix {
	int row;
	int col;
	int[][] arr;
	Scanner scan=new Scanner(System.in);
	int i,j,k;

	public void inputDimension() {
		row=scan.nextInt();
		col=scan.nextInt();
		arr=new int[row][col];
	}

	public void inputMatrix() {
		for( i = 0 ; i < row ; i++ ) {
			for( j = 0 ; j < col ; j++ ) {
				arr[i][j]=scan.nextInt();
			}
		}
	}
		
	public String toString() {
		String str=new String();
		for( i = 0 ; i < row ; i++ ) {
			for( j = 0 ; j < col ; j++ ) {
				str+=arr[i][j]+ " ";
			}
			str+="\n";
		}
		return str; 
	}

	public int checkForAdditionSubstraction(Matrix matrix1,Matrix matrix2) {
		if( (matrix1.row == matrix2.row)  &&  (matrix1.col == matrix2.col) ) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public int checkForMultiplication(Matrix matrix1,Matrix matrix2) {
		if(matrix1.col==matrix2.row) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public Matrix add(Matrix matrix1,Matrix matrix2) {
		Matrix matrix3=new Matrix();
		matrix3.row=matrix1.row;
		matrix3.col=matrix1.col;
		matrix3.arr=new int[matrix3.row][matrix3.col];

		for( i = 0 ; i < matrix1.row ; i++)  {
			for( j = 0 ; j < matrix1.col ; j++ ) {
				matrix3.arr[i][j]=matrix1.arr[i][j] + matrix2.arr[i][j];
			}
		}
		return matrix3;
	}

	public Matrix sub(Matrix matrix1,Matrix matrix2) {
		Matrix matrix3=new Matrix();
		matrix3.row=matrix1.row;
		matrix3.col=matrix1.col;
		matrix3.arr=new int[matrix3.row][matrix3.col];

		for( i = 0 ; i < matrix1.row ; i++)  {
			for( j = 0 ; j < matrix1.col ; j++ ) {
				matrix3.arr[i][j]=matrix1.arr[i][j] - matrix2.arr[i][j];
			}
		}
		return matrix3;
	}

	public Matrix mul(Matrix matrix1,Matrix matrix2) {
		Matrix matrix3=new Matrix();
		matrix3.row=matrix1.row;
		matrix3.col=matrix2.col;
		matrix3.arr=new int[matrix3.row][matrix3.col];

		for( i = 0 ; i < matrix3.row ; i++ ) {
			for( j = 0 ; j < matrix3.col ; j++ ) {
				matrix3.arr[i][j]=0;
			}
		}

		for( i = 0 ; i < matrix3.row ; i++ ) {
			for( j = 0 ; j < matrix3.col ; j++ ) {
				for( k = 0 ; k <matrix1.row ; k++ ) {
					matrix3.arr[i][j]+=(matrix1.arr[i][k]*matrix2.arr[k][j]);
				}
			}
		}

		return matrix3;
	}
}

public class Mat {
	public static void main(String[] args) {

		Matrix matrix1=new Matrix();
		Matrix matrix2=new Matrix();
		Matrix matrix3=new Matrix();
		Matrix object=new Matrix();

		System.out.println("Enter dimensions of matrix 1(row column):");
		matrix1.inputDimension();

		System.out.println("Enter matrix 1:");
		matrix1.inputMatrix();

		System.out.println("Enter dimensions of matrix 2(row column):");
		matrix2.inputDimension();
		System.out.println("Enter matrix 2:");
		matrix2.inputMatrix();

		System.out.println("Matrix 1 is:");
		System.out.println(matrix1);

		System.out.println("Matrix 2 is:");
		System.out.println(matrix2);

		int returnValue=object.checkForAdditionSubstraction(matrix1,matrix2);

		if( returnValue == 1 ) {

			matrix3=object.add(matrix1,matrix2);
			System.out.println("Resultant matrix of addition is:");
			System.out.println(matrix3);

			matrix3=object.sub(matrix1,matrix2);
			System.out.println("Resultant matrix subtraction is:");
			System.out.println(matrix3);
		}
		else {
			System.out.println("Addition and Subtraction can't be performed...");
		}

		returnValue=object.checkForMultiplication(matrix1,matrix2);

		if( returnValue == 1 ) {

			matrix3=object.mul(matrix1,matrix2);
			System.out.println("Resultant matrix of multiplication is:");
			System.out.println(matrix3);
		}
		else {
			System.out.println("Multiplication can't be performed...");
		}
	}
}
	
