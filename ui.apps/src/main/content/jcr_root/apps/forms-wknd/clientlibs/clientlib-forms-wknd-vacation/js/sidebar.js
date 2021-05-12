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
        var node = guideBridge.resolveNode(som);
        if (id === social) {
            $("#" + node.id + " p").html("Activity<br/> <b>Social</b>");
        } else if (id === type) {
            $("#" + node.id + " p").html("Adventure Type<br/> <b>Day Trip</b>");
        } else if (id === length) {
            $("#" + node.id + " p").html("Trip Length<br/> <b>1</b>");
        } else if (id === size) {
            $("#" + node.id + " p").html("Group Size<br/> <b>6 - 8</b>");
        } else if (id === difficulty) {
            $("#" + node.id + " p").html("Difficulty<br/> <b>Beginner</b>");
        } else if (id === price) {
            $("#" + node.id + " p").html("Price<br/> <b>100$ USD</b>");
        } else {
            console.log("Failed to identify the correct id for the given operation");
        }
    }
    catch (e) {
        console.error("Failed to retrieve the som or the id is undefined for the sidebar operation");
    }
}