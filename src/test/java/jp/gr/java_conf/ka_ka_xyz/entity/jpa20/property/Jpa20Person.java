/*
   Copyright [2013] [kakaxyz.kakaxyz@gmail.com]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package jp.gr.java_conf.ka_ka_xyz.entity.jpa20.property;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;

@Access(AccessType.PROPERTY)
public class Jpa20Person {
    
    public Jpa20Person(){}
    public Jpa20Person(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    private int id;
    private String firstName;
    private String lastName;
    
    @Column(name="id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result
                + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jpa20Person other = (Jpa20Person) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }
    
}
