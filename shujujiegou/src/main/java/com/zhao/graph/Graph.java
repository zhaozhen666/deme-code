package com.zhao.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private  int v; //顶点个数
    private LinkedList<Integer> adj[]; //邻接表
    private boolean found =false;
    public Graph(int v){
        this.v =v;
        adj= new LinkedList[v];
        for (int i=0;i<v;++i){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    public boolean bfs(int s,int t){
        boolean [] visited = new boolean[v];
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        visited[s] =true;
        while (!queue.isEmpty()){
            int p = queue.poll();
            if (p==t){
                return true;
            }
            for (int i=0;i<adj[p].size();++i){
                int q= adj[p].get(i);
                if (!visited[q]){
                    visited[q] =true;
                    queue.add(q);
                }
            }
        }
        return false;
    }


    public boolean bfs_print(int s,int t){
        boolean [] visited = new boolean[v];
        Queue<Integer> queue =new LinkedList<>();
        queue.add(s);
        visited[s] =true;
        int [] prev = new int[v];
        for (int i=0;i<v;++i){
            prev[i]=-1;
        }
        while (!queue.isEmpty()){
            int p = queue.poll();
            if (p==t){
                print(prev,s,t);
                return true;
            }
            for (int i=0;i<adj[p].size();++i){
                int q= adj[p].get(i);
                if (!visited[q]){
                    prev[q]=p;
                    visited[q] =true;
                    queue.add(q);
                }
            }
        }
        return false;
    }

    private void print(int [] prev,int s,int t){
        if (prev[t]!=-1&&t!=s){
            print(prev,s,prev[t]);
        }
        System.out.println(t+"");
    }

    public boolean dfs_simple(int s,int t){
        boolean [] visited = new boolean[v];
        return found;
    }

}
