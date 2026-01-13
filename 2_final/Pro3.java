import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pro3 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		RandomAccessFile raf = new RandomAccessFile("C:/Test/album.ppm", "rw");
		FileChannel fc = raf.getChannel();
		
		System.out.println("Input same-size six ppm filenames:");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nf = st.countTokens();
		
		String[] path = new String[nf];
		String[] file = new String[nf];
		
		RandomAccessFile[] inf = new RandomAccessFile[nf];
		FileChannel[] fcin = new FileChannel[nf];
		
		System.out.println("Number of files = " + nf);
	
		for (int i = 0; i < nf; i++) {
			file[i] = st.nextToken();
			path[i] = "C:/Test/" + file[i];
			inf[i] = new RandomAccessFile(path[i], "r");
			fcin[i] = inf[i].getChannel();
		}
		
		System.out.print("Type width height : ");
		String[] dimensions = br.readLine().split("\\s");
		int width = Integer.parseInt(dimensions[0]);
		int height = Integer.parseInt(dimensions[1]);
		
		String header = "P6 " + (width * 3) + " " + (height * 2)+ " 255\n";
		ByteBuffer byteBuf1 = ByteBuffer.allocate(header.length());
		byteBuf1.put(header.getBytes());
		byteBuf1.flip();
		fc.write(byteBuf1);
	

		for (int j = 0; j < height; j++) { 
			for (int k = 0; k < 3; k++) {
				ByteBuffer dataBuf = ByteBuffer.allocate(width * 3); 
				fcin[k].read(dataBuf);
				dataBuf.flip();
				fc.write(dataBuf); 
			}
		}
	
		for (int j = 0; j < height; j++) { 
			for (int k = 3; k < 6; k++) { 
				ByteBuffer dataBuf = ByteBuffer.allocate(width * 3);
				fcin[k].read(dataBuf);
				dataBuf.flip();
				fc.write(dataBuf); 
			}
		}
		ByteBuffer byteBuf2 = ByteBuffer.allocate(header.length());
		fc.position(0);
		fc.read(byteBuf2);
		byteBuf2.flip();
		
		byte[] b = byteBuf2.array();
		System.out.println(new String(b));
		
		for(int i = 0; i<nf; i ++) {
			fcin[i].close();
			inf[i].close();
		}
		fc.close();
		raf.close();
		
	
	}
}
