package com.algorithm.october.zero_five;

import java.util.*;

/**
 * @author Ming
 * @date 2020/10/5 - 11:08
 * @describe
 */
public class LeetCode {

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     */
    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length<4) return new ArrayList<>();
        Arrays.sort(nums);
        if (nums[nums.length-4]+nums[nums.length-3]+nums[nums.length-2]+nums[nums.length-1]<target ||
                nums[0]+nums[3]+nums[2]+nums[1]>target
        ) return new ArrayList<>();
        dfs(nums, target, 0, 0, new ArrayList<>());
        return lists;
    }
    void dfs(int[] nums, int target, int nowNumber, int nowIndex, List<Integer> list){
        if (list.size() > 4 || (list.size()==1 && list.get(0)*4>target) || (!list.isEmpty() && nowNumber*4 > target)) return;
        if (list.size() == 4 && nowNumber == target){
            lists.add(new ArrayList<>(list));
            return;
        }
        int before = Integer.MAX_VALUE,length = nums.length;
        for (int i = nowIndex; i < length; i++){
            if (nums[i]==before) continue;
            list.add(nums[i]);
            dfs(nums, target, nowNumber+nums[i], i+1, list);
            list.remove(list.size()-1);
            before = nums[i];
        }
    }


    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 4) {
                return result;
            }
            Arrays.sort(nums);  // 便于后面的 “去重” 和 “剪枝”
            int length = nums.length;
            for (int index1 = 0; index1 < length - 3; index1++) {  // index1 为结果中 第一个值
                if (index1 > 0 && nums[index1] == nums[index1 - 1]) {
                    continue;
                }

            /*
                剪枝：
                    1、当前最小和 > target，结束“当前层循环”
                    2、当前最大和 < target，跳过“当前循环”
             */
                int curMin = nums[index1] + nums[index1 + 1] + nums[index1 + 2] + nums[index1 + 3];
                if (curMin > target) {
                    break;
                }
                int curMax = nums[index1] + nums[length - 1] + nums[length - 2] + nums[length - 3];
                if (curMax < target) {
                    continue;
                }
                for (int index2 = index1 + 1; index2 < length - 2; index2++) {  // index2 为结果中 第2个值
                    if (index2 > index1 + 1 && nums[index2] == nums[index2 - 1]) {
                        continue;
                    }
                    int index3 = index2 + 1;
                    int maxIndex = length - 1;

                /*
                    剪枝：
                        1、当前最小和 > target，结束“当前层循环”
                        2、当前最大和 < target，跳过“当前循环”
                 */
                    curMin = nums[index1] + nums[index2] + nums[index3] + nums[index3 + 1];
                    if (curMin > target) {
                        break;
                    }
                    curMax = nums[index1] + nums[index2] + nums[maxIndex] + nums[maxIndex - 1];
                    if (curMax < target) {
                        continue;
                    }
                    while (index3 < maxIndex) {  // index3 为结果中 第3个值
                        int curValue = nums[index1] + nums[index2] + nums[index3] + nums[maxIndex];
                        if (curValue == target) {
                            result.add(Arrays.asList(nums[index1], nums[index2], nums[index3], nums[maxIndex]));

                        /*
                            index3、maxIndex去重
                         */
                            index3++;
                            while (index3 < maxIndex && nums[index3] == nums[index3 - 1]) {
                                index3++;
                            }
                            maxIndex--;
                            while (index3 < maxIndex && index2 < maxIndex && nums[maxIndex] == nums[maxIndex + 1]) {
                                maxIndex--;
                            }
                        } else if (curValue > target) {
                            maxIndex--;
                        } else {
                            index3++;
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        LeetCode leetCode = new LeetCode();
        List<List<Integer>> lists = leetCode.fourSum(new int[]{91277418,66271374,38763793,4092006,11415077,60468277,1122637,72398035,-62267800,22082642,60359529,-16540633,
                92671879,-64462734,-55855043,-40899846,88007957,-57387813,-49552230,-96789394,18318594,-3246760,-44346548,-21370279,42493875,25185969,83216261,-70078020,
                -53687927,-76072023,-65863359,-61708176,-29175835,85675811,-80575807,-92211746,44755622,-23368379,23619674,-749263,-40707953,-68966953,72694581,-52328726,
                -78618474,40958224,-2921736,-55902268,-74278762,63342010,29076029,58781716,56045007,-67966567,-79405127,-45778231,-47167435,1586413,-58822903,-51277270,
                87348634,-86955956,-47418266,74884315,-36952674,-29067969,-98812826,-44893101,-22516153,-34522513,34091871,-79583480,47562301,6154068,87601405,-48859327,
                -2183204,17736781,31189878,-23814871,-35880166,39204002,93248899,-42067196,-49473145,-75235452,-61923200,64824322,-88505198,20903451,-80926102,56089387,
                -58094433,37743524,-71480010,-14975982,19473982,47085913,-90793462,-33520678,70775566,-76347995,-16091435,94700640,17183454,85735982,90399615,-86251609,
                -68167910,-95327478,90586275,-99524469,16999817,27815883,-88279865,53092631,75125438,44270568,-23129316,-846252,-59608044,90938699,80923976,3534451,
                6218186,41256179,-9165388,-11897463,92423776,-38991231,-6082654,92275443,74040861,77457712,-80549965,-42515693,69918944,-95198414,15677446,-52451179,
                -50111167,-23732840,39520751,-90474508,-27860023,65164540,26582346,-20183515,99018741,-2826130,-28461563,-24759460,-83828963,-1739800,71207113,26434787,
                52931083,-33111208,38314304,-29429107,-5567826,-5149750,9582750,85289753,75490866,-93202942,-85974081,7365682,-42953023,21825824,68329208,-87994788,3460985,
                18744871,-49724457,-12982362,-47800372,39958829,-95981751,-71017359,-18397211,27941418,-34699076,74174334,96928957,44328607,49293516,-39034828,5945763,
                -47046163,10986423,63478877,30677010,-21202664,-86235407,3164123,8956697,-9003909,-18929014,-73824245}, -236727523);
        lists.forEach(System.out::println);
    }
}
