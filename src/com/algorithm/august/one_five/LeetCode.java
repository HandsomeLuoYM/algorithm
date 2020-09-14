package com.algorithm.august.one_five;

/**
 * @author Ming
 * @date 2020/8/15 - 11:55
 * @describe
 */
public class LeetCode {
    /**
     * 根据官方提示模拟解题
     * 主要是状态规划
     * 例：4,1,4,2,4,4,4
     * 首先安装按照只能从末尾开始删除规则
     * array[4]-array[6]相等 必然删除一起产能获取更大参数值
     * 需要一个状态来保存相等
     * 用三维数组dp[i][j][k] 起始坐标i，结尾坐标j，以j坐标结尾后相同的参数格式为k
     * dp公式推导为dp[i][j][k]=dp[i][j-1][k]+k*k(j-1必然跟位数不同)
     * 参考距离参数为dp[0][4][3]=dp[0][3][0]+3*3=dp[0][3][0]+9;
     * dp[0][3][0]的数组为{4,1,4,2}没有相连的
     * 既dp[0][3][0]=4;
     * dp[0][4][3]=9+4=13
     * 这样计算结果必然不是最大值，从中间删除1,2 然后再删除5这样肉眼都可判断需要执行的结果。
     * 下面进行删除逻辑
     * array[0]==array[4]
     * 如果删除 array[1]-array[3]，这样数组为（4,4,4,4）可以获得数值为4*4+dp[1][3][0]=19=dp            [0][4][4]=19
     * 继续右移
     * 发现 array[2]==array[4]
     * 删除array[3]
     * dp[0][4][4]=dp[0][2][4]+dp[3][3][0];
     * dp[0][2][4]可见array[0]==array[2]=>dp[0][0][5]+dp[1]dp[1][0]
     * 最终结果为   dp[0][0][5]+dp[1][1][0]+dp[3][3][0]=5*5+1+1=27
     * 推导公式为：dp[i][j][k]如果存在i-j之间有一个数x->array[x]==array[j]
     *  dp[i][j][k]= Math.max(dp[i][j][k],dp[i][x][k+1]+dp[x+1][j-1][0]);
     */


    /**
     * 执行用时：49 ms, 在所有 Java 提交中击败了43.95%的用户
     * 内存消耗：51 MB, 在所有 Java 提交中击败了8.69%的用户
     */
    // dp[i][j][k]表示消除[i:j]区间得到的最大收益，其中k表示j之后还存在k个和j相同的字符
    // 1.单独删除的情况dp[i][j][k] = dp[i][j-1][0] + (k+1)*(k+1) 后一项dp的k为0是因为我们
    // 仅考虑[i:j-1]之后没有和j-1处字符相同的字符
    // 2.如果[i:j-1]之间存在某个位置p和j处字符相同的元素，则可以考虑先把[p+1:j-1]处的字符删除，然后剩下dp[i][p][k+1]
    //   也即是dp[i][j][k] = dp[i][p][k+1] + dp[p+1][j-1][0]，在[i:j-1]区间中，枚举所有这样的p，然后求最小值即可
    int[][][] dp = new int[101][101][101];
    public int removeBoxes(int[] boxes) {
        int ans = dfs(boxes,0,boxes.length-1,0);
        return ans;
    }

    private int dfs(int[] boxes, int l, int r, int k) {
        // base case
        if (l>r) return 0;
        if (dp[l][r][k]!=0) return dp[l][r][k];
        // 初始化（r和k），找与队列最后一个相同的个数
        while(r>1&&boxes[r]==boxes[r-1]){
            r=r-1;
            k=k+1;
        }
        int max = 0;
        // 第一种情况 单删
        max = Math.max(max,dfs(boxes,l,r-1,0)+(k+1)*(k+1));
        // 第二种情况 删中间的
        for (int i = l; i <r ; i++) {
            //要和array[r]相同
            if (boxes[i]==boxes[r]) {
                max=Math.max(max,(dfs(boxes,l,i,k+1)+dfs(boxes,i+1,r-1,0)));
            }
        }
        //标记，方便后续判断
        dp[l][r][k] = max;
        return dp[l][r][k];
    }


}
