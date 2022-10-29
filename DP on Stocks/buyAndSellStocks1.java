class buyAndSellStocks1{
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - min;
            maxProfit = Math.max(maxProfit, profit);
            min = Math.min(min , prices[i]);
        }
        System.out.println("max profit is : " + maxProfit);
    }
}