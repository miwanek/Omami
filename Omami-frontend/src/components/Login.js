import React, {Component} from 'react'
import '../login/Login.css'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: ""
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        event.preventDefault();
    }

    render() {
        return (
            <div className="Login">
                <form onSubmit={this.handleSubmit}>
                    <label className="label">Email: </label>
                    <Form.Group controlId="email" bsSize="large">
                        <Form.Control
                            autoFocus
                            type="email"
                            value={this.state.email}
                            onChange={this.handleChange}
                        />
                    </Form.Group>
                    <label className="label">Password: </label>
                    <Form.Group controlId="password" bsSize="large">
                        <Form.Control className="form"
                            value={this.state.password}
                            onChange={this.handleChange}
                            type="password"
                        />
                    </Form.Group>

                    <Button
                        block
                        size="lg"
                        disabled={!this.validateForm()}
                        type="submit"
                    >
                        Login
                    </Button>
                </form>


            </div>

        );
    }
}