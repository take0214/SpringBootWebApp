import styles from './PopUp.module.css'

import AddTraining from "./Contents/AddTraining"
import AddRecord from "./Contents/AddRecord"
import DeleteTraining from "./Contents/DeleteTraining"
import DeleteRecord from "./Contents/DeleteRecord"

export default function PopUp({ targetData, targetRecord, popUp, setPopUp }) {

    if (popUp === 'off') {
        return (
            <></>
        )
    } else if (popUp === 'training') {
        return (
            <>
                <div className={styles.popUp}>
                    <AddTraining setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'workout') {
        return (
            <>
                <div className={styles.popUp}>
                    <AddRecord targetData={targetData} setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'delete_training') {
        return (
            <>
                <div className={styles.popUp}>
                    <DeleteTraining targetData={targetData} setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'delete_record') {
        return (
            <>
                <div className={styles.popUp}>
                    <DeleteRecord targetData={targetData} targetRecord={targetRecord} setPopUp={setPopUp} />
                </div>
            </>
        )
    }
}