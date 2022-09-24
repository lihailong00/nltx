// app.js
App({
  // 曾经报错
  globalData: {},

  onLaunch: function () {
    if (wx.cloud) {
      wx.cloud.init({
        traceUser: true
      })
    }
    wx.getSystemInfo({
      success: e => {
        this.globalData.StatusBar = e.statusBarHeight;
        let capsule = wx.getMenuButtonBoundingClientRect();
        if (capsule) {
          this.globalData.Custom = capsule;
          this.globalData.CustomBar = capsule.bottom + capsule.top - e.statusBarHeight;
        } else {
          this.globalData.CustomBar = e.statusBarHeight + 50;
        }
      }
    })

    // 自动登录
    console.log("启动自动登录");
    wx.login({
      success: res => {
        let code = res.code;
        console.log("code=", code);
        wx.request({
          url: "https://xiaoyy.oulushengwu.com/login",
          method: "POST",
          data: {
            'js_code': code
          },
          success: res => {
            console.log("成功获取token");
            console.log(res);
            console.log("token获取完毕");
            wx.setStorageSync('token', res.data.data);
          }
        });
      },
      fail: res => {
        wx.showToast({
          title: '登录失败'
        })
      }
    });
  }

})