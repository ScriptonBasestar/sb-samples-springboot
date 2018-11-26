# springboot-member_crud

## 사용기술

* spring-boot
* spring-framework
* spring-security
* h2
* mysql


## 기능

* 회원가입
* 로그인

* 회원 목록보기
* 회원 상세보기

## kotlin

확장함수 function extension
확장프로퍼티
vararg
infix
destructing declaration

method in method, inner method

abstract interface 경계가 더욱 모호

동등성비교 .equals ==
참조비교 ===

is !is in !in
other == null || other !is Client 똑같이 쓰면 other !is Client

delegate pattern
class DelegatingCollection<T> ( innerList: Collection<T> = ArrayList<T>()
): Collection<T> by innerList {}
Collection의 구현을 innerList에 위임.

singleton
언어에서 객체선언 기능을 통해 싱글턴을 기본 지원.
객체선언 = 클래스 선언 + 그 클래스에 속한 단일인스턴스 선언을 합친 선언

object Payroll{
  val allEmployees = arrayListOf<Person>()
}

자바의 진화형. 쓰레기 자바를 호환시키다 보니 장애문법이 상당수 발견

window.addMouseListener(
  object: MouseAdapter(){
    override fun mouseClicked(e: MouseEvent){}
    override fun mouseEntered(e: MouseEvent){}
  }
)

바운드 멤버참조


all, any, count, filter size
asSequence(), stream()
filter, map
flatMap

추상메서드가 하나만 있는 interface
함수형인터페이스(functional interface) or SAM 인터페이스
SAM: Single abstract method

postponeComputation(1000, object: Runnable{
    override fun run(){
    }
})

postponeComputation(1000) {}
