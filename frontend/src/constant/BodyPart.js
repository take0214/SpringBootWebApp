export const BodyPart = {
    CHEST: 'chest',
    SPINE: 'spine',
    LEG: 'leg',
    SHOULDER: 'shoulder',
    ARM: 'arm',
    ABS: 'abs'
};
Object.freeze(BodyPart);// プロパティを凍結

export function changeJp(value) {
    if (value === BodyPart.CHEST) {
        return '胸'
    } else if (value === BodyPart.SPINE) {
        return '背中'
    } else if (value === BodyPart.LEG) {
        return '脚'
    } else if (value === BodyPart.SHOULDER) {
        return '肩'
    } else if (value === BodyPart.ARM) {
        return '腕'
    } else if (value === BodyPart.ABS) {
        return '腹筋'
    }
}







