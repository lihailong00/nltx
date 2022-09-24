// index.js
Page({
  handleTest: function() {
    console.log("启动测试");
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/getmyposting',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: (res) => {
        console.log(res);
      }
    })
  },
  clickCreateGoods: function() {
    wx.navigateTo({
      url: '/pages/view/create-goods/create-goods',
    })
  },

  clickCreatePosting: function() {
    console.log("发布帖子");
    wx.navigateTo({
      url: '/pages/view/create-posting/create-posting',
    })
  },

  clickSayLove: function() {
    wx.navigateTo({
      url: '/pages/view/create-say-love/create-say-love',
    })
  },

  clickMyRepo: function() {
    wx.navigateTo({
      url: '/pages/view/my-repo/my-repo',
    })
  },

  data: {
    // 表白墙内容属性

    // 表白墙分页属性
    currentPage: 1,
    pageSize: 10,
    sayLoveList: []
  },

  initInfo: function() {
    this.data.currentPage = 1;
    this.data.sayLoveList = [];
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/saylove/listsaylovebyapge',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage: this.data.currentPage,
        pageSize: this.data.pageSize
      },
      success: res => {
        console.log(res);
        this.setData({
          currentPage: this.data.currentPage + 1,
          sayLoveList: res.data.data
        });
      }
    })
  },
  // 加载表白墙前10条信息
  onLoad() {
    this.initInfo();
  },

  // 触底加载更多信息
  onReachBottom() {
    wx.showLoading({
      title: '加载更多对象',
    })

    wx.request({
        url: 'https://xiaoyy.oulushengwu.com/saylove/listsaylovebyapge',
        method: "POST",
        header: {
          "authorization": wx.getStorageSync('token')
        },
        data: {
          currentPage: this.data.currentPage,
          pageSize: this.data.pageSize
        },
        success: res => {
          this.setData({
            currentPage: this.data.currentPage + 1,
            sayLoveList: this.data.sayLoveList.concat(res.data.data)
          });
          wx.hideLoading();
        }
      })
  },

  // 下拉刷新
  onPullDownRefresh: function() {
    this.initInfo();
    wx.showToast({
      title: '刷新成功',
    })
    wx.stopPullDownRefresh({
      success: (res) => {
      },
    })
  }
})
