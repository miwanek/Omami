import React, {Component} from 'react'

class Logout extends Component {
    render() {
        return (
            <div className= "logout">
                <ul> <a href>Logout</a>
                    <h3>Hi, {this.props.userName}</h3>
       
                </ul>
            </div>
        )
    }
}

export default Logout