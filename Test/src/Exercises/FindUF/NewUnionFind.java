package Exercises.FindUF;

public class NewUnionFind {
    private int[] id;
    private int[] sz;
    private int[] max;

    public NewUnionFind(int N) {
        id = new int[N];
        sz = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
            max[i] = i;
        }
    }

    private int root(int i) {
        int obj = i;
        while (obj != id[obj]) {
            id[obj] = id[id[obj]];
            obj = id[obj];
        }
        return obj;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int i) {
        return max[root(i)];
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            if (max[j] < max[i]) {
                max[j] = max[i];
            }
        } else {
            id[j] = i;
            sz[i] += sz[j];
            if (max[i] < max[j]) {
                max[i] = max[j];
            }
        }
    }
}

// ----------------- Problem 2: Implement a find method ------------------

/*

public class Main {
    public static void main(String[] args) {
        NewUnionFind uf = new NewUnionFind(10);
        uf.union(1, 2);
        uf.union(6, 2);
        uf.union(7, 6);
        int i = 1;
        System.out.println("The max of the tree containing " + i + " is: " + uf.find(i));
    }
}

* */
