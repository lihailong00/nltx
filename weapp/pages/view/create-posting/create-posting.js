import WxValidate from '../../../util/WxValidate.js';

Page({

  /**
   * 页面的初始数据
   */
  data: {
  },

  initValidate() {
    // 验证字段的规则
    const rules = {
      title: {
        required: true,
        rangelength: [1, 20]
      },
      content: {
        required: true,
        rangelength: [1, 200]
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      title: {
        required: "请输入标题",
        rangelength: "标题不宜过长"
      },
      content: {
        required: "请输入内容",
        rangelength: "内容不宜过多"
      }
    }

    // 创建实例对象
    this.WxValidate = new WxValidate(rules, messages)
  },

  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },

  formSubmit(e) {
    const params = e.detail.value

    console.log("参数是");
    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }
    
    // 表单验证成功
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/posting/createposting',
      method: "POST",
      data: {
        title: params.title,
        content: params.content
      },
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        wx.showToast({
          title: '发布成功！',
          duration: 2000,
        })
        setTimeout(() => {
          wx.switchTab({
            url: '/pages/tabs/life/life',
          })
        }, 2000);
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initValidate();
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