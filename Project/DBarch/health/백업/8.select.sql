SELECT ����, COUNT(*) AS �ο���
FROM ȸ��ī��
GROUP BY ����;

SELECT (���⼼ + ������ + PT_Ʈ���̳�_���� + ���� + �Ҹ�ǰ��) AS ��_������
FROM ������
LIMIT 1;

SELECT �̸�, 
       LENGTH(���_ȸ��_����Ʈ) - LENGTH(REPLACE(���_ȸ��_����Ʈ, ',', '')) + 1 AS ����Ƚ��
FROM Ʈ���̳�
WHERE ���_ȸ��_����Ʈ IS NOT NULL AND ���_ȸ��_����Ʈ != '';

SELECT �̸�, DATEDIFF(������, CURDATE()) AS ����_�̿��ϼ�
FROM ȸ��ī��
WHERE �̸� = '�迹��';

SELECT �̸�, PT_����_����
FROM Ʈ���̳�;

SELECT �̸�, CONCAT('���� ���� �ð�: ', �ٹ�_�ð�ǥ) AS ����_����
FROM Ʈ���̳�;

SELECT CONCAT('��Ǫ ', ��Ǫ, ', ���� ', ����, ', ��� ', ���, ', ���� ', ����, ', ������ ', ������) AS �Ҹ�ǰ_��Ȳ
FROM �Ҹ�ǰ
LIMIT 1;

SELECT �ⱸ��, ����
FROM ��ⱸ
WHERE ���� = '����';

SELECT DATE_FORMAT(������, '%Y-%m') AS ���Կ�, COUNT(*) AS �ο���
FROM ȸ��ī��
WHERE ���� = 'M' AND ���� >= 20
GROUP BY ���Կ�;
