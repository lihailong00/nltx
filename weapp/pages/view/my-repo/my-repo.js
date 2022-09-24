// pages/view/my-repo/my-repo.js
Page({
  deleteGoods: function (e) {
    console.log("删除商品");
    console.log(e);
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/deletegoodsbygoodsid',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        goodsId: e.currentTarget.dataset.goodsinfo.goods_id
      },
      success: res => {
        wx.showToast({
          title: '删除成功！',
        })
        this.initInfo();
      }
    })
  },
  deletePostings: function (e) {
    console.log("删除帖子");
    console.log(e);
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/deletepostingbypostingid',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        postingId: e.currentTarget.dataset.postinginfo.posting_id
      },
      success: res => {
        wx.showToast({
          title: '删除成功！',
        })
        this.initInfo();
      }
    })
  },
  deleteSayLove: function (e) {
    console.log("删除表白墙");
    console.log(e);
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/saylove/deletesaylove',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        sayLoveId: e.currentTarget.dataset.sayloveinfo.say_love_id
      },
      success: res => {
        wx.showToast({
          title: '删除成功！',
        })
        this.initInfo();
      }
    });
  },

  onChange(event) {
    this.data.itemType = event.detail.name;
    // wx.showToast({
    //   title: `切换到标签 ${event.detail.name}`,
    // });
  },

  initInfo() {
    // 获取我的交易记录
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbyuserid',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        this.setData({
          goodsList: res.data.data
        });
      }
    })

    console.log("发帖记录");
    // 获取我的发帖记录
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/getmyposting',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        this.setData({
          posting: res.data.data
        });
      }
    })

    console.log("表白墙记录");
    // 获取我的表白墙记录
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/saylove/listsaylovebytoken',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        this.setData({
          sayLoveList: res.data.data
        });
      }
    })
  },

  /**
   * 页面的初始数据
   */
  data: {
    // 【我的发布】帖子类型
    itemType: 0,

    // 【我的发布】每种帖子的集合
    goodsList: [],
    posting: [],
    sayLoveList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("获取我的交易");
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
  onPullDownRefresh: function () {
    this.initInfo();
    wx.showToast({
      title: '刷新成功',
    })
    wx.stopPullDownRefresh({
      success: (res) => {
      },
    })
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})