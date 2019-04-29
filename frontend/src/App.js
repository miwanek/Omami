import React, {Component} from 'react';
import Login from "./components/Login"
import Chat from "./components/Chat"
import Message from "./components/Message"
import MessageList from "./components/MessageList"
import NewRoomForm from "./components/NewRoomForm"
import RoomList from "./components/RoomList"
import SendMessageForm from "./components/SendMessageForm"
import './style.css';

const tempData = [
    {
        senderId: 'one',
        text: 'Hello everyone'
    },
    {
        senderId: 'two',
        text: 'Hello'
    },
    {
        senderId: 'three',
        text: 'Well, hi'
    },
]

class App extends Component {

    constructor() {
        super()
        this.state = {
            messsages: []
        }
        this.sendMessage = this.sendMessage.bind(this)
        // this.addMessages = this.addMessages.bind(this)
    }

    sendMessage(text) {
        this.currentUser.sendMessage({
            text
        })
    }

    addMessages() {
        this.setState({
            messages: [...this.state.messages, tempData]
        })
        return this.state.messages
    }


    render() {
        return (
            <div className="main">
                <div className="app">
                    <RoomList/>
                    <MessageList
                        messages={tempData} />
                    <NewRoomForm/>
                    <SendMessageForm
                        sendMessage={this.sendMessage} />
                </div>
                {/*<div className="Login">
                    <Login/>
                </div>*/}

            </div>
        );
    }
}

export default App;
