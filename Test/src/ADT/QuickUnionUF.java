package ADT;

public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        int obj = i;
        while (obj != id[obj]) {
            id[obj] = id[id[obj]];// Path compression
            obj = id[obj];
        }
        return obj;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        id[root(p)] = root(q);
    }
}
