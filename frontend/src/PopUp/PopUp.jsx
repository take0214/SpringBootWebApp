import "react-datepicker/dist/react-datepicker.css"

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
                <div id="overlay">
                    <AddTraining setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'workout') {
        return (
            <>
                <div id="overlay">
                    <AddRecord targetData={targetData} setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'delete_training') {
        return (
            <>
                <div id="overlay">
                    <DeleteTraining targetData={targetData} setPopUp={setPopUp} />
                </div>
            </>
        )
    } else if (popUp === 'delete_record') {
        return (
            <>
                <div id="overlay">
                    <DeleteRecord targetData={targetData} targetRecord={targetRecord} setPopUp={setPopUp} />
                </div>
            </>
        )
    }
}