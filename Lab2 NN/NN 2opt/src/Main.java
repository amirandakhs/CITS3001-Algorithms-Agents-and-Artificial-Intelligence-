/**
 * Uses 2-OPT repeatedly to improve cs, choosing the shortest option in each iteration.
 * You can assume that cs is a valid tour initially.
 * table[i][j] == table[j][i] gives the cost of travel between City i and City j.
 **/
     public static int[] tsp2opt(int[] cs, double[][] table)
    {
        boolean flag = true;
        while (flag) {
            flag =false;
            /** changing each two edge if the total path decreased we 
             * change the edges
             */
            for (int i = 1; i < cs.length - 2; i++) {
                for (int j = i + 1; j < cs.length - 1; j++) {
                    int[] temp = changing_city(cs, i, j);
                    if (path(temp, table) < path(cs,table)) {
                        cs = temp;
                        flag = true;
                    }
                }
            }
            // this will if there is cross between last edge with all edges
            for (int j = 1; j < cs.length - 1; j++) {
                int[] temp = changing_last_city(cs,j);
                if (path(temp, table) < path(cs,table)) {
                    cs = temp;
                    flag = true;
                }

            }



        }
        return cs;
    }
    /** 
     * We start the root from 0 city to city i then connect it to j
     *  then from j reversly from j to i+1 at the end connect it to 
     * j+1 and then to the end of the rout
    */
    public static int[] changing_city(int[] cs, int i,int j){
        int[] new_tour = new int[cs.length];
        //start from city 0 to i
        for(int x =0; x <= i-1; x++ ){
            new_tour[x] = cs[x];
        }
        //adding reversely tour to make the cycle
        int rev =0;
        for (int x = i; x <= j; x++){
            new_tour[x] = cs[j-rev];
            rev++;
        }

        //adding the rest of cities
        for(int x= j+1; x< cs.length;x++){
            new_tour[x] = cs[x];
        }
        return new_tour;

    }
    /** 
     * same procedure as the the changing city except we start 
     * from city 0 
     * 
    */
    public static int[] changing_last_city(int[] cs, int j){
        int[] new_tour = new int[cs.length];
        for(int x =0; x <= j; x++ ){
            new_tour[x] = cs[x];
        }
        int rev = 0;
        for (int x = j+1; x<=cs.length -1; x++){
            new_tour[x] = cs[cs.length -1 -rev];
            rev++;
        }
        return new_tour;

    }
    // Finding the total length of path 
    public static double path(int[] route, double[][] table){
        double sum =0.0;
        for (int i=0; i < route.length -1; i++ ){
            sum += table[route[i]][route[i + 1]];
        }
        sum += table[route[route.length-1]][route[0]];
        return sum;
    }