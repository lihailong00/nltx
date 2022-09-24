// pages/view/create-goods/create-goods.js
import WxValidate from '../../../util/WxValidate.js';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 标签属性
    goodsType: 0, // 0代表卖东西，1表示买东西
    picker: [
      "买家",
      "卖家"
    ],
  },

  // 表单验证数据初始化
  initValidate() {
    // 验证字段的规则
    const rules = {
      name: {
        required: true,
        rangelength: [1, 20]
      },
      currentPrice: {
        required: true,
        number: true
      },
      contact: {
        required: true,
        rangelength: [1, 40]
      },
      description: {
        required: true,
        rangelength: [1, 100]
      }
    }

    // 验证字段的提示信息，若不传则调用默认的信息
    const messages = {
      name: {
        required: "请输入商品名称",
        rangelength: "商品名不宜过长"
      },
      currentPrice: {
        required: "请输入金额",
        number: "请输入正确金额"
      },
      contact: {
        required: "请填写您的联系方式",
        rangelength: [1, 40]
      },
      description: {
        required: "请填写商品描述",
        rangelength: "描述信息不宜过长"
      }
    }

    // 创建实例对象
    this.WxValidate = new WxValidate(rules, messages)
  },

  // 错误信息提示
  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
  },

  formSubmit(e) {
    const params = e.detail.value

    console.log(params)

    // 传入表单数据，调用验证方法
    if (!this.WxValidate.checkForm(params)) {
      const error = this.WxValidate.errorList[0]
      this.showModal(error)
      return false
    }

    // 表单验证成功
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/creategoods',
      method: "POST",
      data: {
        name: params.name,
        originalPrice: params.originalPrice,
        currentPrice: params.currentPrice,
        contact: params.contact,
        description: params.description,
        goodsType: this.data.picker[this.data.goodsType]
      },
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: res => {
        console.log(res);
        if (res.data.success) {
          wx.showToast({
            title: '商品发布成功！',
          })
          setTimeout(() => {
            wx.switchTab({
              url: '/pages/tabs/trade/trade',
            })
          }, 1500);
        }
      }
    })
  },


  pickerChange(e) {
    this.setData({
      goodsType: e.detail.value
    });
    console.log(this.data.goodsType);
  },
  /**
   * 生命周期函数--监听页面加载
   */

  onLoad(options) {
    this.initValidate()
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