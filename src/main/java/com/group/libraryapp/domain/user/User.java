package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;
  @Column(nullable = false, length = 20) //name varchar(20)
  private String name;
  private Integer age;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,orphanRemoval = true)
  private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

  public User(String name, Integer age) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
    }
    this.name = name;
    this.age = age;
  }

  public void updateName(String name){
    this.name = name;
  }

  public void loanBook(String bookName){
    this.userLoanHistories.add(new UserLoanHistory(this, bookName));
  }

  public void returnBook(String bookName){
    UserLoanHistory targetHistory = this.userLoanHistories.stream()
            .filter(history -> history.getBookName().equals(bookName)) //파라매터로 받은 bookName하고 같은지 보고
            .findFirst()  //첫번째로 걸리는걸 찾아서
            .orElseThrow(IllegalArgumentException::new); //혹시 갖고있는 유저가 없으면 익셉션 처리
    targetHistory.doReturn();
  }

}
