import React, {Component} from 'react';
import Login from "./components/Login"
import Chat from "./components/Chat"
import Message from "./components/Message"
import MessageList from "./components/MessageList"
import NewRoomForm from "./components/NewRoomForm"
import RoomList from "./components/RoomList"
import SendMessageForm from "./components/SendMessageForm"
import './style.css';

const tempRooms = [
    "Good Room",
    "The Best Room",
    "Omami"
];

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            rooms: []
        };

        this.sendMessage = this.sendMessage.bind(this)
        this.createRoom= this.createRoom.bind(this)
    }

    sendMessage(text) {
        this.setState({
            messages: [...this.state.messages, text]
        });
    }

    createRoom(room) {
        this.setState ( {
            rooms: [...this.state.rooms, room]
        })
    }

    render() {
        return (
            <div className="main">
                {/*<div className="Login"><Login/></div>*/}
                <div className="app">
                    <RoomList rooms={[...this.state.rooms, ...tempRooms]}/>
                    <MessageList
                        messages={this.state.messages} />
                    <NewRoomForm createRoom={this.createRoom}/>
                    <SendMessageForm
                        sendMessage={this.sendMessage} />
                </div>
            </div>
        );
    }
}

export default App;
