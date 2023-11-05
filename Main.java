import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String[] args) {
        int adjMat [] [] = {
                {0, 2, 3, 4, 1, 0, 0},
                {2, 0, 2, 3, 0, 0, 0},
                {2, 4, 0, 0, 2, 3, 0},
                {1, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 2, 0, 0, 2},
                {0, 0, 0, 0, 0, 1, 0}};

        imprimirMatrizAdj(adjMat);
        imprimirListaAdj(adjMat);
        
	System.out.println("\n====Escriba el nodo desde el que se inicia la trayectoria (Desde el 1 al 7)====\n");
		String inicioId = System.console().readLine();
		int nodoINICIO = Integer.parseInt(inicioId);

        int source = nodoINICIO;
        int[] distance = dijkstra(adjMat, source - 1); 
        
        for(int i = 0; i < adjMat.length; i++) {
            encontrarTrayectoria(adjMat, source - 1, i, distance); 
        }
    }
    
    public static void imprimirMatrizAdj(int[][] adjMat) {
        System.out.println("Matriz de Adyacencia:");
        for(int[] row : adjMat) {
            for(int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    
    public static void imprimirListaAdj(int[][] adjMat) {
        System.out.println("\nLista de Adyacencia:");
        for(int i = 0; i < adjMat.length; i++) {
            System.out.print("Nodo " + (i + 1) + ": "); 
            for(int j = 0; j < adjMat[i].length; j++) {
                if(adjMat[i][j] != 0) {
                    System.out.print((j + 1) + "(" + adjMat[i][j] + ") "); 
                }
            }
            System.out.println();
        }
    }
    
	public static int[] dijkstra(int[][] adjMat, int source) {
        int[] distance = new int[adjMat.length];
        boolean[] visited = new boolean[adjMat.length];
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        
        for(int i = 0; i < adjMat.length; i++) {
            int minDistVertex = findMinDistVertex(distance, visited);
            visited[minDistVertex] = true;
            for(int j = 0; j < adjMat.length; j++) {
                if(adjMat[minDistVertex][j] != 0 && !visited[j]) {
                    int newDist = distance[minDistVertex] + adjMat[minDistVertex][j];
                    if(newDist < distance[j]) {
                        distance[j] = newDist;
                    }
                }
            }
        }
        
        return distance;
    }

	public static int findMinDistVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        for(int i = 0; i < distance.length; i++) {
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }
    
    public static void encontrarTrayectoria(int[][] adjMat, int source, int destination, int[] distance) {
        List<Integer> path = new ArrayList<>();
        int currentNode = destination;
        
        while(currentNode != source) {
            path.add(currentNode + 1);
            for(int i = 0; i < adjMat.length; i++) {
                if(adjMat[i][currentNode] != 0 && distance[currentNode] - adjMat[i][currentNode] == distance[i]) {
                    currentNode = i;
                    break;
                }
            }
            if(currentNode == source) {
                path.add(source + 1);
                break;
            }
        }
        
        Collections.reverse(path);
        System.out.println("=====================================================");
        System.out.print("De " + (source + 1) + " a " + (destination + 1) + ", son " + (path.size() - 1) + " enlaces y el camino es ");
        for(int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if(i < path.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n=====================================================");
    }

	
}
