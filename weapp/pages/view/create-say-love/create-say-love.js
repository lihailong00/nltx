import WxValidate from '../../../util/WxValidate.js';

Page({

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
      url: 'https://xiaoyy.oulushengwu.com/saylove/insertsaylove',
      method: "POST",
      data: {
        title: params.title,
        content: params.content
      },
      header: {
        "authorization": wx.getStorageSync('token')
      },
      success: (res) => {
        console.log(res)
        wx.showToast({
          title: '成功发布表白墙！',
          duration: 1500
        })
        setTimeout(() => {
          wx.switchTab({
            url: '/pages/tabs/index/index',
          })
        }, 1500);
      }
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
  },

  showModal(error) {
    wx.showModal({
      content: error.msg,
      showCancel: false,
    })
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

  onLoad() {
    this.initValidate()
  },
})