package com.algorithm.two_zero_two_zero.interview;

/**
 * @author Ming
 * @date 2020/9/1 - 19:14
 * @describe
 */
public class DuoDuo {

    /**
     * 第一题
     */
    private void print(int n){
        if (n%2==0){
            int half = n/2;
            for (int i =0;i<n;i++){
                String str = "";
                for (int j = 0;j<n;j++){
                    if (i<j&&i<=half&&j<half  &&i!=j&&i+j!=n-1) str += 2+" ";
                    else if (i<n-1-j && i<=half && j>=half && i!=j && i+j!=n-1) str += 1+" ";
                    else if (i<half && j<half &&i!=j&&i+j!=n-1) str += 3+" ";
                    else if (i<n-1-j && i>=half && j<=half &&i!=j&&i+j!=n-1) str += 4+" ";
                    else if (i>=n-1-j && i>=half && j<half &&i!=j&&i+j!=n-1) str += 5+" ";
                    else if (i>j&&i>=half && j>=half && i!=j&&i+j!=n-1) str += 6+" ";
                    else if (i<j&&i>=half && j>=half && i!=j&&i+j!=n-1) str += 7+" ";
                    else if (i<half && j>=half && i!=j && i+j!=n-1) str += 8+" ";
                    else str += 0+" ";
                }
                System.out.println(str.trim());
            }
        }else {
            int  half = n/2;
            for (int i =0;i<n;i++){
                String str = "";
                for (int j = 0;j<n;j++){
                    if (i<j&&i<=half&&j<half  &&i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 2+" ";
                    else if (i<n-1-j && i<=half && j>=half && i!=j && i+j!=n-1&&i!=half&&j!=half) str += 1+" ";
                    else if (i<half && j<half &&i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 3+" ";
                    else if (i<n-1-j && i>=half && j<=half &&i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 4+" ";
                    else if (i>=n-1-j && i>=half && j<half &&i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 5+" ";
                    else if (i>j&&i>=half && j>=half && i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 6+" ";
                    else if (i<j&&i>=half && j>=half && i!=j&&i+j!=n-1&&i!=half&&j!=half) str += 7+" ";
                    else if (i<half && j>=half && i!=j && i+j!=n-1&&i!=half&&j!=half) str += 8+" ";
                    else str += 0+" ";
                }
                System.out.println(str.trim());
            }
        }
    }

    public static void main(String[] args) {
        DuoDuo duoDuo = new DuoDuo();
        duoDuo.print(6);
    }

}
