class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        int upper_limit = (int)(Math.sqrt(2 * N + 0.25) - 0.5);
        for (int k = 1; k <= upper_limit; ++k) {
            N -= k;
            if (N % k == 0)
                count++;
        }
        return count;
    }
    
    // public int consecutiveNumbersSum(int N) {
    //     // N > 0
    //     int res = 0;
    //     for (int l = 0; l * (l + 1) / 2 < N; l++) {
    //         if ((N - (l * (l + 1) / 2)) % (l + 1) == 0) {
    //             res++;
    //         }
    //     }
    //     return res;
    // }
}
