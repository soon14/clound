/**
 * Created by wuxinx on 2019/2/28.
 * 生成水印
 */

'use strict'

let watermark = {}
const id = '1.23452384164.123412415'

let setWatermark = (base64) => {
    if (document.getElementById(id) !== null) {
        document.body.removeChild(document.getElementById(id))
    }

    let div = document.createElement('div')
    div.id = id
    div.style.pointerEvents = 'none'
    div.style.top = '120px'
    div.style.left = '100px'
    div.style.position = 'fixed'
    div.style.zIndex = '100000'
    div.style.width = document.documentElement.clientWidth - 100 + 'px'
    div.style.height = document.documentElement.clientHeight - 100 + 'px'
    div.style.background = 'url(' + base64 + ') left top repeat'
    document.body.appendChild(div)
    return id
}

/**
 * 设置水印 该方法只允许调用一次
 */
watermark.set = (base64) => {
    let id = setWatermark(base64)
    setInterval(() => {
        if (document.getElementById(id) === null) {
            id = setWatermark(base64)
        }
    }, 500)
    window.onresize = () => {
        setWatermark(base64)
    }
}

/**
 * 删除水印
 */
watermark.clear = () => {
    let div = document.getElementById(id)
    if(div !== null){
        div.style.background = 'none'
    }
}

export default watermark