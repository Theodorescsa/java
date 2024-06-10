#include<stdio.h>
#include<string.h>
#include<stdlib.h>
struct maytinh {
	char hang[11];
	char chip[3];
	char code[21];
	float price;
	int ram;
};
struct maytinh* scanf_maytinh(int n) {
	struct maytinh* list_maytinh = (struct maytinh*) malloc(n*sizeof(struct maytinh));
	for (int i = 0; i < n; i++)
    {
        int chip_choice;
        printf("Moi nhap hang cua may tinh: ");
        scanf("%s", list_maytinh[i].hang);
        printf("Moi nhap ram cua may tinh: ");
        scanf("%d",&list_maytinh[i].ram);
        printf("Moi nhap gia cua may tinh: \n");
        scanf("%f",&list_maytinh[i].price);
        printf("Moi nhap chip cua may tinh:\n1:i3\n2:i5\n3:i7 \n");
        getchar();
        scanf("%d",&chip_choice);
        switch (chip_choice)
        {
        case 1:
            strcpy(list_maytinh[i].chip,"i3");
            break;
        case 2:
            strcpy(list_maytinh[i].chip,"i5");
            break;
        case 3:
            strcpy(list_maytinh[i].chip,"i7");
            break;
        default:
            break;
        }
    }
    return list_maytinh;
}
void setCode(struct maytinh array[],int quantity) {
    for(int i = 0;i< quantity;i++) {
        printf("Moi nhap ma cho may tinh %s",array[i].hang);
        scanf("%s",array[i].code);
    }
}
void sortbyprice(struct maytinh array[],int quantity) {
    struct maytinh temp;
    for(int i =0;i< quantity;i++) {
        if (i==quantity-1) {
            break;
        }
        if (array[i+1].price<array[i].price) {
            temp = array[i];
            array[i] = array[i+1];
            array[i+1] = temp;
            i=-1;
        }
    }
}
int main() {
	int quantity = 2;
	struct maytinh* list = scanf_maytinh(quantity);
	setCode(list, quantity);
	sortbyprice(list,quantity);
	for(int i = 0;i<quantity;i++) {
		printf("%s\n",list[i].code);
	}
}