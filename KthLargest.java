class KthLargest {
    int k;
    int[] nums;
    public KthLargest(int k, int[] nums) {
        this.k=k;
        this.nums=nums;
    }
    
    public int add(int val) {
        int[] nums2 = new int[nums.length+1];
        int i=0;
        for (int m:nums) {
            nums2[i++]=m;
        }
        
        nums2[nums.length] = val;
        nums=nums2;
        
        int n=nums.length-k;
        quickSelect(0,nums.length-1,n,nums);
        return nums[n];
    }
    public static void quickSelect(int left, int right, int k, int[] nums) {
        if (left==right) return;
        int pivot = partition(left,right,k,nums);
        if (pivot==k) return;
        else if (pivot>k) {
            quickSelect(left,pivot-1,k,nums);
        } else {
            quickSelect(pivot+1,right,k,nums);
        }
    }
    public static int partition(int left, int right, int k, int[] nums) {
        Random rdm=new Random();
        int pivot=left+rdm.nextInt(right-left);
        swap(pivot,right,nums);
        int j=left;
        for (int i=left;i<right;i++) {
            if (nums[i]<nums[right]) {
                swap(i,j,nums);
                j++;
            }
        }
        swap(j,right,nums);
        return j;
    }
    public static void swap(int a, int b, int[] nums) {
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

//maxHeap much faster
class KthLargest {
    int k;
    int[] nums;
    Queue<Integer> heap;
    public KthLargest(int k, int[] nums) {
        this.k=k;
        this.nums=nums;
        heap=new PriorityQueue<>((x,y)->x-y);
        for (int n:nums) {
            heap.add(n);
            if (heap.size()>k) heap.poll();
        }
    }
    
    public int add(int val) {
        if (heap.isEmpty()) {
            heap.add(val);
        } else {
            if (heap.size()<k) heap.add(val);
            else if (val>heap.peek()) {
            heap.add(val);
            heap.poll();
            }
        }
        return heap.peek();
    }
    
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
