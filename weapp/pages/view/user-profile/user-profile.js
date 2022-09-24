// pages/view/user-profile/user-profile.js
Page({
  submitUserProfile() {
    console.log("提交用户列表");
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/user/setuserinfo',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        username: this.data.username,
        realName: this.data.realName
      },
      success: (res) => {
        console.log(res);
        if (res.data.success) {
          wx.showToast({
            title: '修改成功！',
            duration: 2000
          })
          setTimeout(() => {
            wx.switchTab({
              url: '/pages/tabs/home/home',
            })
          }, 2000);
        } else {
          wx.showToast({
            title: res.data.msg,
          })
        }
      },
      fail: (res) => {},
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
    major: "",
    school: "",
    wechatNum: "",
    degree: "",
    studentId: "",
    username: "",
    dept: "",
    description: "",
    realName: "",
    email: "",
    phoneNum: "",
    qqNum: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 从后端获取个人信息
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/user/getuserallinfo',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: (res) => {
        console.log(res);
        wx.showToast({
          title: '初始化个人信息',
        })
        this.setData({
          username: res.data.data.username,
          major: res.data.data.major,
          school: res.data.data.school,
          wechatNum: res.data.data.wechat_num,
          degree: res.data.data.degree,
          studentId: res.data.data.student_id,
          dept: res.data.data.dept,
          description: res.data.data.description,
          realName: res.data.data.real_name,
          email: res.data.data.email,
          phoneNum: res.data.data.phone_num,
          qqNum: res.data.data.qqNum
        });
      },
      fail: (res) => {
        wx.showToast({
          title: '未能从后端获取信息',
        })
      },
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
  onPullDownRefresh() {

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