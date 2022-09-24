// pages/tabs/trade/trade.js
Page({
  switchTabs(e) {
    this.data.goodsType = e.detail.name;
    console.log(this.data.goodsType);
    console.log("切换到界面：" + this.data.goodsType + "。排序方式是：" + this.data.sortType);
  },

  switchSortType(e) {
    this.data.sortType = e.detail;
    if (this.data.goodsType === 0) {
      console.log("卖家发布界面。" + "排序方式：" + this.data.sortType);
      // 清空【卖家发布】界面
      this.setData({
        goodsList0: [],
        currentPage0: 1
      });
      // 加载【卖家发布】界面
      wx.request({
        url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage0',
        method: "POST",
        header: {
          "authorization": wx.getStorageSync('token')
        },
        data: {
          currentPage0: this.data.currentPage0,
          pageSize: this.data.pageSize,
          keyword: this.data.keyword,
          goodsType: 0,
          sortType: this.data.sortType
        },
        success: res => {
          console.log(res);
          this.setData({
            currentPage0: this.data.currentPage0 + 1,
            goodsList0: this.data.goodsList0.concat(res.data.data)
          });
        }
      })
    }
    if (this.data.goodsType === 1) {
      console.log("买家悬赏界面。" + "排序方式：" + this.data.sortType);
      // 清空【买家悬赏】界面
      this.setData({
        goodsList1: [],
        currentPage1: 1
      });
      // 加载【买家悬赏】界面
      wx.request({
        url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage1',
        method: "POST",
        header: {
          "authorization": wx.getStorageSync('token')
        },
        data: {
          currentPage1: this.data.currentPage1,
          pageSize: this.data.pageSize,
          keyword: this.data.keyword,
          goodsType: 1,
          sortType: this.data.sortType
        },
        success: res => {
          console.log(res);
          this.setData({
            currentPage1: this.data.currentPage1 + 1,
            goodsList1: this.data.goodsList1.concat(res.data.data)
          });
        }
      })
    }
  },

  onSearch() {
    console.log(this.data.keyword);
    this.data.goodsList0 = [];
    this.data.goodsList1 = [];
    this.data.currentPage0 = 1;
    this.data.currentPage1 = 1;
    // 加载卖家发布
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage0',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage0: this.data.currentPage0,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword,
        goodsType: 0,
        sortType: this.data.sortType
      },
      success: res => {
        console.log(res);
        this.setData({
          currentPage0: this.data.currentPage0 + 1,
          goodsList0: this.data.goodsList0.concat(res.data.data)
        });
      }
    })
    // 加载买家悬赏
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage1',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage1: this.data.currentPage1,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword,
        goodsType: 1,
        sortType: this.data.sortType
      },
      success: res => {
        console.log(res);
        this.setData({
          currentPage1: this.data.currentPage1 + 1,
          goodsList1: this.data.goodsList1.concat(res.data.data)
        });
      }
    })
  },

  // 处理【联系ta】
  handleOrder(e) {
    console.log(e);
    const goodsInfo = e.currentTarget.dataset.goodsinfo;
    console.log("商品信息：" + goodsInfo.create_time);
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/handlegoods',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        goodsId: goodsInfo.goods_id,
        receiverId: goodsInfo.user_id
      },
      success: res => {
        if (res.data.success === true) {
          wx.showToast({
            title: '成功拍下商品！',
          })
          setTimeout(() => {
            wx.switchTab({
              url: '/pages/tabs/home/home',
            })
          }, 1500);
        } else {
          wx.showToast({
            title: '手速慢了一点',
          })
        }
      }
    })
  },


  /**
   * 页面的初始数据
   */
  data: {
    // 搜索
    keyword: "",

    // 商品属性
    // 0表示卖家发布，1表示买家悬赏
    goodsList0: [],
    currentPage0: 1,
    goodsList1: [],
    currentPage1: 1,
    pageSize: 10,

    // 商品是买还是卖
    goodsType: 0,

    // 排序方式属性
    option: [{
        text: '新品优先',
        value: 0
      },
      {
        text: '价格优先',
        value: 1
      }
    ],
    sortType: 0
  },

  initInfo: function () {
    this.data.goodsList0 = [];
    this.data.goodsList1 = [];
    this.data.currentPage0 = 1;
    this.data.currentPage1 = 1;
    // 加载卖家发布
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage0',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage0: this.data.currentPage0,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword,
        goodsType: 0,
        sortType: this.data.sortType
      },
      success: res => {
        console.log(res);
        this.setData({
          currentPage0: this.data.currentPage0 + 1,
          goodsList0: this.data.goodsList0.concat(res.data.data)
        });
      }
    })

    // 加载买家悬赏
    wx.request({
      url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage1',
      method: "POST",
      header: {
        "authorization": wx.getStorageSync('token')
      },
      data: {
        currentPage1: this.data.currentPage1,
        pageSize: this.data.pageSize,
        keyword: this.data.keyword,
        goodsType: 1,
        sortType: this.data.sortType
      },
      success: res => {
        console.log(res);
        this.setData({
          currentPage: this.data.currentPage1 + 1,
          goodsList1: this.data.goodsList1.concat(res.data.data)
        });
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log("加载交易界面");
    this.initInfo();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    wx.showLoading({
      title: '加载更多商品',
    })
    // 为何暂停0.5s?就是玩儿~
    setTimeout(() => {
      // 加载卖家发布
      if (this.data.goodsType === 0) {
        wx.request({
          url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage0',
          method: "POST",
          header: {
            "authorization": wx.getStorageSync('token')
          },
          data: {
            currentPage0: this.data.currentPage0,
            pageSize: this.data.pageSize,
            keyword: this.data.keyword,
            goodsType: 0,
            sortType: this.data.sortType
          },
          success: res => {
            this.setData({
              currentPage0: this.data.currentPage0 + 1,
              goodsList0: this.data.goodsList0.concat(res.data.data)
            });
            console.log(this.data.goodsList0);
          }
        })
      }
      // 加载买家悬赏
      if (this.data.goodsType === 1) {
        wx.request({
          url: 'https://xiaoyy.oulushengwu.com/goods/listgoodsbypage1',
          method: "POST",
          header: {
            "authorization": wx.getStorageSync('token')
          },
          data: {
            currentPage0: this.data.currentPage1,
            pageSize: this.data.pageSize,
            keyword: this.data.keyword,
            goodsType: 0,
            sortType: this.data.sortType
          },
          success: res => {
            this.setData({
              currentPage1: this.data.currentPage1 + 1,
              goodsList1: this.data.goodsList1.concat(res.data.data)
            });
            console.log(this.data.goodsList1);
          }
        })
      }
      wx.hideLoading()
    }, 500);

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
      },
    })
  },



  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})