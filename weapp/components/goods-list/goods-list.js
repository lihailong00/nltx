// components/goods-list/goods-list.js
Page({
  data: {
    // 排序方式属性
    option: [
      { text: '新品优先', value: 0 },
      { text: '价格优先', value: 1 }
    ],
    sortType: 0,
  },
  onShow() {
    console.log("加载商品");
  }
})
