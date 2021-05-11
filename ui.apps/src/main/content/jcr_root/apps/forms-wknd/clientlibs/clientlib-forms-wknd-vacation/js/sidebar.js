const social = "SOCIAL";
const type = "TYPE";
const length = "LENGTH";
const size = "SIZE";
const difficulty = "DIFFICULTY";
const price = "PRICE";

/**
 * Retrieve the details of the sidebar
 * @name sideBarProp Returns the value of the sidebar
 * @param {string} som The som expression of the field. Note that the only som expressions supported are for text components
 * @param {string} id The identifier of the field used to determine what text to set in the som expression.
 */
function sideBarProp(som, id) {
    try {
        let node = guideBridge.resolveNode(som);
        if (id === social) {
            $("#" + node.id + " p").html("Activity<br/> <b>Social</b>");
        }

        if (id === type) {
            $("#" + node.id + " p").html("Adventure Type<br/> <b>Day Trip</b>");
        }

        if (id === length) {
            $("#" + node.id + " p").html("Trip Length<br/> <b>1</b>");
        }

        if (id === size) {
            $("#" + node.id + " p").html("Group Size<br/> <b>6 - 8</b>");
        }

        if (id === difficulty) {
            $("#" + node.id + " p").html("Difficulty<br/> <b>Beginner</b>");
        }

        if (id === price) {
            $("#" + node.id + " p").html("Price<br/> <b>100$ USD</b>");
        }
    } catch(e) {
        console.error("Failed to set the side bar value for som")
    }

}