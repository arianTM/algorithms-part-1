package Exercises.Network;

public class NetworkUF {
    // We will use Weighted Union Find with Path compression
    private int[] id;
    private int[] sz;

    public NetworkUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        int obj = i;
        while (obj != id[obj]) {
            id[obj] = id[id[obj]]; // Path compression
            obj = id[obj];
        }
        return obj;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public boolean union(int p, int q) {
        if (connected(p, q)) {
            return false;
        }
        int pRoot = root(p);
        int qRoot = root(q);
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            return sz[qRoot] == id.length;
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            return sz[pRoot] == id.length;
        }
    }
}

// ------------------- Problem 1: Network members --------------------------
/*

import Exercises.Network.NetworkUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String cwd = System.getProperty("user.dir");
        NetworkUF uf = new NetworkUF(5);
        try {
            File myFile = new File(cwd + "\\src\\Exercises\\Network\\logfile.txt");
            Scanner fileReader = new Scanner(myFile);
            boolean allConnected;
            int iterator = 1;
            while (fileReader.hasNextLine()) {
                int p = fileReader.nextInt();
                int q = fileReader.nextInt();
                allConnected = uf.union(p, q);
                if (allConnected) {
                    System.out.println("The earliest timestamp is: " + iterator);
                    return;
                }
                iterator++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error ocurred:");
            e.printStackTrace();
        }
    }
}

* */
