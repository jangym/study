package vending;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/*
    # 관리자모드
    1. 재고추가 addStock()
    2. 가격변경 updatePrice()
        3. 상품추가 addItem()
        4. 상품삭제 delete()
    5. 잔여금액 getTotal()
    6. 현금추가  // 수익 증가(관리자) addMoney()
    7. 현금반환  // 재고 감소(관리자) returnMoney()
    8. 수익 getSalesTotal()
    9. 자판기 가동
    0. 소비자모드  // return;

    # 소비자모드
    1. 현금 카드 선택 paymentMangement()
    2. 자판기 잔액 경고 warning()
    3. 음료목록 출력 => 선택 itemList()
    4. 품절 확인 soldOut() -> 재고가 0이 아니면 음료목록에 있음.
    5. 금액 투입 / 잔돈 계산 (/추가결제여부)   // 수익 증가(관리자) / 재고 감소(관리자)
        5-1. (잔돈) >= (음료 중 최저가격) ->
    6. 카드결제 payCard()
    7. 결제 완료 메세지 paymentCompleted()
    8. 추가결제  // 나중에 -> addPayment

 */

public class VendingUI {
	// 입력 받기 BufferedReader
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Vending vv = new VendingImpl();
	private UserVO vo1 = new UserVO();

	public void menu() {
		int ch;
		System.out.println("----------------------");
		System.out.println("\t자판기");
		System.out.println("----------------------");
		while (true) {
			try {
				listAll();
				// 음료출력
				System.out.println("1.현금 2.카드");
				// System.out.println("0. 관리자모드 9. 종료");
				System.out.print("결제수단 선택 => ");

				ch = Integer.parseInt(br.readLine());

				if (ch == 9) {
					System.out.println("프로그램을 종료합니다.");
					break;
				}

				if (ch == 0) {
					System.out.print("비밀번호 입력 => ");
					String inputPw = br.readLine();

					if ("0135".equals(inputPw)) { // 임시 비밀번호 0135 << 0으로 시작하면, 00 = 0으로 인식함(int).
						adminMenu();
					} else {
						System.out.println("비밀번호 오류.\n");
					}
					continue; // while(true)
				}

				switch (ch) {
				case 1:
					payCash();
					break;
				case 2:
					payCard();
					break;
				default:
					System.out.println("잘못된 선택.\n"); // switch case 이외

				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
			} catch (Exception e) {
				System.out.println("입력 오류입니다.");
			}
		}
	}

	private void adminMenu() {
		while (true) {
			try {
				int ch;
				System.out.println("관리자 모드입니다.");
				System.out.println("1.재고추가 2.가격변경 3.상품추가 4.상품삭제");
				System.out.println("5.잔액및 수익현황 6.현금추가 7.현금반환 0.소비자모드");
				System.out.print("번호 선택 => ");
				ch = Integer.parseInt(br.readLine());

				switch (ch) {
				case 1:
					addStock();
					break;
				case 2:
					updatePrice();
					break;
				case 3:
					addItem();
					break;
				case 4:
					delete();
					break;
				case 5:
					vendingtotal();
					break;

				case 6:
					addcash();
					break;
				case 7:
					returncash();
					break;

				case 0:
					System.out.println("자판기 가동을 시작합니다.");
					System.out.println("소비자 모드로 돌아갑니다.");
					return;
				default:
					System.out.println("잘못된 메뉴입니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
			} catch (Exception e) {
				System.out.println("입력 오류입니다.");
			}
		}
	}

	// 음료 선택
	public String choseItem() {
		String name = " ";

		try {
			listAll();
			System.out.print("음료를 선택하시오 => ");
			name = br.readLine();

			VendingVO vo = vv.findByName(name);

			if (vo == null) {
				System.out.println("등록된 음료가 없습니다.");
				System.out.println();
			} else if (vo.getStock() <= 0) { // 재고가 0일 때 품절
				System.out.println("해당 제품은 품절입니다.");
				System.out.println();
			}

		} catch (Exception e) {

		}
		return name;

	}

	// 카드결제
	public void payCard() {
		try {
			String name = choseItem();
			VendingVO vo = vv.findByName(name);

			if (vv.reduceStock(name)) {
				
				vo1.setPaycard(vo1.getPaycard() + vo.getPrice());
				vo1.setSalestot(vo1.getSalestot() + vo.getPrice());
				System.out.println("결제가 완료되었습니다.");
				System.out.println("[결제 정보]");
				System.out.println("상품명 : " + name + ", 가격 : " + vo.getPrice());
				 // 여기도 전역 vo1 사용
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void payCash() {
		try {
			System.out.print("돈을 투입하시오 => ");
			vo1.setInsert_money(Integer.parseInt(br.readLine())); // 금액 입력

			String name = choseItem();
			VendingVO vo = vv.findByName(name);

			if (vo1.getInsert_money() < vo.getPrice()) {
				System.out.println("금액이 부족합니다.");
				System.out.println("투입한 금액 " + vo1.getInsert_money() + "원을 반환합니다.");
				return;
			}

			if (vv.reduceStock(name)) {
				vo1.setInsert_money(vo1.getInsert_money() - vo.getPrice());
				vo1.setAddcash(vo1.getAddcash() + vo.getPrice());
				vo1.setSalestot(vo1.getSalestot() + vo.getPrice());

				System.out.println("결제가 완료되었습니다.");
				System.out.println("[결제 정보]");
				System.out.println("상품명 : " + name + ", 가격 : " + vo.getPrice());
				System.out.println("잔여 금액 : " + vo1.getInsert_money() + "원");
				System.out.println();

				// 추가 결제
				if(vo1.getInsert_money() > 0) {
					addPayment(vo1);
				} else {
					System.out.println("잔여금액 0원으로 초기화면으로 돌아갑니다.");
				}
				
			} else {
				System.out.println("재고가 부족합니다.");
			}

		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력하세요.");
		} catch (Exception e) {
			System.out.println("입력 오류 발생");
			e.printStackTrace();
		}
	}

	public void addPayment(UserVO vo1) {
        try {
            while (true) {
                System.out.print("추가 결제를 하시겠습니까? (Y/N) => ");
                String input = br.readLine();

                if (input.equalsIgnoreCase("N")) {
                    System.out.println("잔돈 " + vo1.getInsert_money() + "원을 반환합니다.");
                    break;
                }

                listAll();
                System.out.print("추가로 구매할 음료 선택 => ");
                String name = br.readLine();
                VendingVO vo = vv.findByName(name);

                if (vo == null || vo.getStock() == 0) {
                    System.out.println("해당 상품은 구매할 수 없습니다.");
                    continue;
                } if (vo1.getInsert_money() < vo.getPrice()) {
                    System.out.println("금액이 부족합니다.");
                    System.out.print("추가로 돈을 넣으시겠습니까? (Y/N) => ");
                    String charge = br.readLine();
                    if (charge.equalsIgnoreCase("Y")) {
                        try {
                            System.out.print("추가 금액 => ");
                            int extra = Integer.parseInt(br.readLine());

                            if (extra <= 0) {
                                System.out.println("0원 이하의 금액은 입력될 수 없습니다.");
                                continue; // 다시 처음으로
                            }

                            vo1.setInsert_money(vo1.getInsert_money() + extra);
                            System.out.println("현재 잔액: " + vo1.getInsert_money());

                        } catch (NumberFormatException e) {
                            System.out.println("숫자만 입력해주세요.");
                            continue;
                        } catch (Exception e) {
                                System.out.println("입력 중 오류가 발생했습니다.");
                                continue;
                        }
                        } else {
                            System.out.println("잔돈 " + vo1.getInsert_money() + "원을 반환합니다.");
                            break;
                        }
                    }

                    if (vv.reduceStock(name)) {
                        vo1.setInsert_money(vo1.getInsert_money() - vo.getPrice());
                        System.out.println("추가 결제가 완료되었습니다.");
                        System.out.println("[결제 정보]");
                        System.out.println("상품명 : " + name + ", 가격 : " + vo.getPrice());
                        System.out.println("잔여 금액 : " + vo1.getInsert_money() + "원");

                        if (vo1.getInsert_money() == 0) {
                            System.out.println("잔액이 0원이므로 추가 결제를 종료합니다.");
                            break;
                        }
                    } else {
                        System.out.println("재고가 부족합니다.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

				

	public void addStock() {
		System.out.println("\n[음료 수량 변경]");
		String name;

		try {
			System.out.println("변경할 제품명?");
			name = br.readLine();

			VendingVO vo = vv.findByName(name);
			if (vo != null) {
				System.out.println("추가할 수량");
				vo.setStock(vo.getStock() + Integer.parseInt(br.readLine()));// 수량 변경 = (기존수량+입력한 수량)
				
				System.out.printf("변경된 %s의 수량 %d\n", name, vo.getStock());
			} else {
				System.out.println("등록된 데이터가 없습니다");
			}

		} catch (Exception e) {
		}

	}

	public void updatePrice() {

		System.out.println("\n[음료 가격 변경]");
		String name;
		try {
			System.out.println("변경할 제품명?");
			name = br.readLine();

			VendingVO vo = vv.findByName(name);
			if (vo != null) {
				System.out.println("변경할 가격");
				vo.setPrice(Integer.parseInt(br.readLine()));

				System.out.printf("%s 의 가격이 %d원으로 변경되었습니다\n", name, vo.getPrice());
			} else {
				System.out.println("등록된 데이터가 없습니다");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addItem() {
		System.out.println("\n[새로운 음료 등록]");
		try {
			VendingVO vo = new VendingVO();

			System.out.print("음료명?");
			vo.setName(br.readLine());

			System.out.print("가격?");
			try {
				vo.setPrice(Integer.parseInt(br.readLine()));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			System.out.print("채워넣을 재고?");
			vo.setStock(Integer.parseInt(br.readLine()));

			vv.addItem(vo);

			System.out.println("상품이 등록되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete() {
		System.out.println("\n[상품제거]");

		String name;

		try {
			System.out.print("삭제할 상품 ?");
			name = br.readLine();

			boolean b = vv.removeItem(name);

			if (b) {
				System.out.println(name + " 음료가 삭제되었습니다.");
			} else {
				System.out.println("등록된 상품이 아닙니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public void vendingtotal() {
		System.out.println("현재 잔액 ");
		try {
			int CashSales = vo1.getAddcash();
			int CardSales = vo1.getPaycard();
			int Totalsales = CashSales + CardSales;
			int Tot = vo1.getBalance() + CashSales;

			System.out.printf("시재 : %d\n", Tot);
			System.out.printf("현금 매출 : %d\n",CashSales );
			System.out.printf("카드 매출 : %d\n", CardSales);
			System.out.printf("총 매출 : %d\n", Totalsales);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void addcash() {
		System.out.println("[현금 추가]");

		try {
			System.out.print("추가할 금액? ");
			int a = Integer.parseInt(br.readLine());
			
			if(a <= 0) {
				System.out.println("0원 이하는 출금 불가능합니다.");
			} else {
				vo1.setBalance(vo1.getBalance() + a);
				System.out.println("금액이 추가되었습니다");
				System.out.println("변경된 금액 : " + vo1.getBalance());
			}


		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력 가능합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println();
	}

	public void returncash() {
		System.out.println("[현금인출]");

		try {
			System.out.println("인출할 금액?");
			int a = Integer.parseInt(br.readLine());

			if(a <= 0) {
				System.out.println("0원 이하는 출금 불가능합니다.");
			}

			if ((vo1.getBalance() - a) >= 5000 && a > 0) {
				vo1.setBalance(vo1.getBalance() - a);

				System.out.println("금액이 인출되었습니다.");
				System.out.println("변경된 금액 : "+ vo1.getBalance());
			} else {
				System.out.println("출금 실패했습니다.");
				
			}
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력 가능합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	public void listAll() {
		System.out.println("\n[전체 리스트]");

		List<VendingVO> list = vv.findByAll();

		for (VendingVO vo : list) {
			System.out.println(vo);
		}
		System.out.println();

	}
}