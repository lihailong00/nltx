// pages/tabs/life/life.js
Page({
  onSearch() {
    this.data.postingList = [];
    this.data.currentPage = 1;
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/getpostingbypage',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage: this.data.currentPage,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword
      },
      timeout: 0,
      success: (res) => {
        this.setData({
          currentPage: this.data.currentPage + 1,
          postingList: this.data.postingList.concat(res.data.data)
        });
      },
      fail: (res) => {
        console.log("后台访问失败");
      }
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
    // 下拉框选择
    option: [
      { text: '时间优先', value: 0 },
      // { text: '热度优先', value: 1 }
    ],
    value: 0,

    // 帖子数量属性设置
    currentPage: 1,
    pageSize: 10,
    keyword: "",
    postingList: []
  },

  initInfo: function() {
    this.data.currentPage = 1;
    this.data.postingList = [];
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/getpostingbypage',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage: this.data.currentPage,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword
      },
      timeout: 0,
      success: (res) => {
        this.setData({
          currentPage: this.data.currentPage + 1,
          postingList: this.data.postingList.concat(res.data.data)
        });
      },
      fail: (res) => {
        console.log("后台访问失败");
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initInfo();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  // 下拉刷新
  onPullDownRefresh: function () {
    this.initInfo();
    wx.showToast({
      title: '刷新成功',
    })
    wx.stopPullDownRefresh({
      success: (res) => {
        console.log("下拉");
        console.log(res);
      },
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    wx.showLoading({
      title: '加载更多帖子',
    });

    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/getpostingbypage',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage: this.data.currentPage,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword
      },
      success: (res) => {
        this.setData({
          currentPage: this.data.currentPage + 1,
          postingList: this.data.postingList.concat(res.data.data)
        });
        wx.hideLoading();
      },
      fail: (res) => {
        console.log("后台访问失败");
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})