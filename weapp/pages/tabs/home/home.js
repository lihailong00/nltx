// pages/tabs/home/home.js
import {
  Base64
} from '../../../util/Base64';

Page({
  // 后续功能更新
  showModal(e) {
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },
  submitAdvice() {
    setTimeout(() => {
      wx.showToast({
        title: '消息已成功送达给程序猿小哥！',
      })
    }, 1000);
    this.setData({
      modalName: null
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
    username: "",
    userId: "",
    homeMsg: [],
  },

  // 跳转到个人界面
  openUserProfile: function () {
    wx.navigateTo({
      url: '/pages/view/user-profile/user-profile',
    })
  },

  deleteMsg(e) {
    let homeMsgId = e.currentTarget.dataset.msginfo.home_msg_id;
    console.log("测试删除个人消息");
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/homemsg/deletehomemsg',
      data: {
        homeMsgId: homeMsgId
      },
      header: {
        "authorization": wx.getStorageSync('token')
      },
      method: "POST",
      success: (res) => {
        console.log(res);
        wx.showToast({
          title: '删除成功！',
        })
        this.onLoad();
      },
      fail: (res) => {
        wx.showToast({
          title: '后端程序请求失败',
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 初始化用户信息
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/user/getuserallinfo',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        this.setData({
          username: res.data.data.username,
          userId: res.data.data.user_id
        });
        // 回调地狱
        // 加载homeMsg消息流
        wx.request({
          url: 'https://xiaoyy.oulushengwu.com/homemsg/getmsgbyuserid',
          method: "POST",
          data: {
            userId: this.data.userId
          },
          header: {
            "authorization": wx.getStorageSync('token')
          },
          success: res => {
            console.log(res);
            this.setData({
              homeMsg: res.data.data
            });
          },
          fail: err => {
            console.log("未知错误：" + err);
          }
        })
      },
      fail: err => {
        console.log("未知错误：" + err);
      }
    })


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
    this.onLoad();
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