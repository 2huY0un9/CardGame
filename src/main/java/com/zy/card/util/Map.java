package com.zy.card.util;

import java.util.*;

public class Map {
    private int n = 16; // 顶点数量
    private int m = 15; // 边的数量
    private List<List<Integer>> MapGraph = new ArrayList<>();//地图
    private List<Integer> Used = new ArrayList<>();
    public Map(int n,int m){
        this.n = n;
        this.m = m;
    }
    public  Map(){}

    public void InitMapGraph(){
        Random random = new Random();

        // 初始化图的结构
        for (int i = 0; i < n; i++) {
            MapGraph.add(new ArrayList<>());
        }

        int target = n-1;



        //使n-2,n-3,n-4号顶点指向目标
        for(int i=1;i<=3;i++)
        {
            MapGraph.get(target-i).add(target);
            Used.add(target-i);
        }

        //创建剩余边
        int floor = 0;
        while (Used.size()<=n-4)
        {
            int ok=0;
            do {
                int from = random.nextInt(target-3);
                if(!Used.contains(from)){
                    int r = random.nextInt(3);
                    MapGraph.get(from).add(Used.get(r+floor*3));

                    if(r%2==1)
                    {
                        r = random.nextInt(3);
                        MapGraph.get(from).add(Used.get(r+floor*3));
                    }

                    Used.add(from);
                    ok++;
                }
            }while (ok<3);
            floor++;
        }

        System.out.println(floor);

        for (int j : Used) {
            System.out.print(j + " ");
        }

        System.out.println();

        // 打印图的结构
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
            for (int j : MapGraph.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    public List<List<Integer>> getMapGraph() {
        return MapGraph;
    }

    public List<Integer> getUsed() {
        return Used;
    }

    private static int getInDegree(List<List<Integer>> graph, int vertex) {
        int inDegree = 0;
        for (List<Integer> edges : graph) {
            if (edges.contains(vertex)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    // 获取顶点的出度
    private static int getOutDegree(List<List<Integer>> graph, int vertex) {
        return graph.get(vertex).size();
    }
}
