import java.io.*;
import java.util.*;

public class Main {

	private static int DIM = 0;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {


		//	Leitura do input
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");

		//primeira linha
		
		int s = Integer.parseInt(line[0]);
		int p = Integer.parseInt(line[1]);
		int c = Integer.parseInt(line[2]);
		int counter = 0;
		DIM = s*2;
		
		ArrayList<Integer>[] nodes = new ArrayList[DIM];
		int[] degree = new int[DIM];

		//no de entrada e saida de cada suspeito (init)

		for (int i = 0; i < (DIM); i++) {
			//nodes.add(i, new ArrayList<>());
			nodes[i] = new ArrayList<>();
			degree[i] = 0;
			if(i>0 && i%2 == 1) {
				nodes[i-1].add(i);
				degree[i]++;
			}

		}


		while(counter < (p+c)) {
			//line reading
			String[] nextLine = in.readLine().split(" ");
			int x = Integer.parseInt(nextLine[0]) *2;
			int y = Integer.parseInt(nextLine[1]) *2;


			//precedencias
			if(counter < p) {
				nodes[x+1].add(y);
				degree[y]++;
			}	

			//igualdades
			else {
				nodes[y].add(x+1);
				degree[x+1]++;
				nodes[x].add(y+1);
				degree[y+1]++;
			}
			counter++;

		}
		in.close();
		boolean consistent = acyclic(nodes, degree);

		if(consistent == false) {
			System.out.println("Inconsistent conjectures");
		}
		else {
			System.out.println("Consistent conjectures");
		}	

	}

	private static boolean acyclic(ArrayList<Integer>[] inNodes, int[] degree) {
		int  numProcNodes = 0;
		Queue<Integer> ready = new LinkedList<Integer>();

		for (int i = 0; i < inNodes.length; i++) {
			if(degree[i] == 0)
				ready.add(i);
		}

		while(!ready.isEmpty()) {
			int v = ready.remove();
			numProcNodes++;

			for (int node = 0; node < inNodes[v].size(); node++) {
				degree[inNodes[v].get(node)]--;
				if(degree[inNodes[v].get(node)] == 0) {
					ready.add(inNodes[v].get(node));
				}
			}

		}
		return numProcNodes == inNodes.length;
	}
}
